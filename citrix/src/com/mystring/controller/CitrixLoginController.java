package com.mystring.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citrix.authentication.tokens.AccessToken;
import com.citrix.authentication.tokens.UserDomainPasswordCredentials;
import com.citrix.wi.servletenvironment.WIServletStaticAdaptor;
import com.citrix.wing.AccessTokenValidity;
import com.citrix.wing.AppLaunchInfo;
import com.citrix.wing.AppLaunchParams;
import com.citrix.wing.ICAFile;
import com.citrix.wing.UserEnvironmentAdaptor;
import com.citrix.wing.config.MPSConnectionConfig;
import com.citrix.wing.config.MPSFarmConfig;
import com.citrix.wing.servletadaptor.ServletStaticAdaptor;
import com.citrix.wing.servletadaptor.ServletUserAdaptor;
import com.citrix.wing.types.ClientType;
import com.citrix.wing.types.ServiceAddress;
import com.citrix.wing.webpn.FarmBinding;
import com.citrix.wing.webpn.MPSFarm;
import com.citrix.wing.webpn.UserContext;
import com.citrix.wing.webpn.WebPN;
import com.citrix.wing.webpn.WebPNBuilder;

@Controller
@RequestMapping("/")
public class CitrixLoginController {
 private static final Logger logger = Logger.getLogger(CitrixLoginController.class);
	@RequestMapping({ "/login" })
	public void login(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {
		// Global global= new Global(server,port);
		String username=request.getParameter("username");
		String domain=request.getParameter("domain");
		String password=request.getParameter("password");
		String appId=request.getParameter("appId");
		String ip=request.getParameter("ip");
		String port=request.getParameter("port");
		String url=request.getParameter("url");
		initialGlobal(session,ip,Integer.valueOf(port));
 		WebPN webPN = (WebPN) session.getAttribute("WebPN");
		AccessToken credentials = new UserDomainPasswordCredentials(username, domain, password);
		AccessTokenValidity result = webPN.checkAccessTokenWithExpiry(credentials); // 如果认证成功
		if (result.getValidationResult().isSuccess()) {
			ServletUserAdaptor envAdaptor = new ServletUserAdaptor(request, response);
			envAdaptor.initialize();
			WebPNBuilder builder = (WebPNBuilder) request.getSession().getAttribute("WebPNBuilder");
			UserContext userContext = builder.recoverUserContext(webPN, envAdaptor);
			
			if (userContext == null) {
				// The UserContext has not yet been created for this session, so
				// create a new one.
				userContext = builder.createInitialUserContext(webPN, envAdaptor);
				//AccessToken accessToken = (AccessToken) request.getSession().getAttribute("AccessToken");
				
				// Add all the configured farms to the context, associating the user's
				// credentials with each one.
				MPSFarm[] availableFarms = webPN.getMPSFarms();
				Set activeFarms = userContext.getActiveFarms();
				for (int ix = 0; ix < availableFarms.length; ++ix) {
					//logger.info("===>"+availableFarms[ix].getName()+accessToken);
					activeFarms.add(new FarmBinding(availableFarms[ix].getName(), credentials));
				}		 
			}
			AppLaunchInfo	launchInfo = (AppLaunchInfo)userContext.launchApp(appId, new AppLaunchParams(ClientType.ICA_30));
			ICAFile icaFile = userContext.convertToICAFile(launchInfo, null, null);
			icaFile.setValue(icaFile.getAppName(), "LongCommandLine",url);//请求参数 
             icaFile.setValue(icaFile.getAppName(), "TWIMode", "On"); //Enables the seamless mode for the connection
             /*
 			 * WFClient 
 			 */
             icaFile.setValue("WFClient", "RemoveICAFile", "No");
             icaFile.setValue("WFClient", "TransparentKeyPassthrough", "Local");	
            icaFile.setICAEncoding("UTF8"); //设置编码格式 
			InputStream bis = new ByteArrayInputStream(icaFile.toString().getBytes("utf-8"));
			response.setContentType("application/x-ica; charset=UTF-8");
            response.setHeader("Content-Disposition", "filename=" + java.net.URLEncoder.encode(icaFile.getAppName() + ".ica", "UTF-8") );
            OutputStream bos = response.getOutputStream(); 
            byte[] buff = new byte[1024];
            int readCount = 0;
            if(bis!=null){
                readCount = bis.read(buff);
                while (readCount != -1){
                    bos.write(buff, 0, readCount); 
                    readCount = bis.read(buff);
                }
            }
            if (bis!=null){
                bis.close();
            }
            if (bos!=null){
                bos.close();
            }
            UserEnvironmentAdaptor adaptor = userContext.getEnvironmentAdaptor();
            userContext.close();
            adaptor.commitState();
            adaptor.destroy();
		}		 
	}
	@RequestMapping({ "/logout" })
	public void logout(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {
		session.invalidate();
 	}
	private void initialGlobal(HttpSession session,String ip,int port) throws Exception {

		ServiceAddress xmlServiceAddress = new ServiceAddress(ip, port);

		ServletContext servletContext = session.getServletContext();

		ServletStaticAdaptor staticEnvAdaptor = null;
		try {

			staticEnvAdaptor = new WIServletStaticAdaptor(servletContext);
		} catch (Throwable t) {

		}

		// Create a WebPNBuilder instance.
		WebPNBuilder builder = WebPNBuilder.getInstance();
		// request.getSession().setAttribute("WebPNBuilder", builder);
		session.setAttribute("WebPNBuilder", builder);
		MPSFarmConfig farmConfig = new MPSFarmConfig("Farm1");
		farmConfig.addConnection(new MPSConnectionConfig(xmlServiceAddress));
		com.citrix.wing.config.Configuration config = new com.citrix.wing.config.Configuration();
		config.addMPSFarmConfig(farmConfig);

		// Create a WebPN instance from the configuration.
		WebPN webPN = null;
		webPN = builder.createWebPN(config, staticEnvAdaptor);
		session.setAttribute("WebPN", webPN);

	}
}