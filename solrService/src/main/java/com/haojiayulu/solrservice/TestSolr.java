package com.haojiayulu.solrservice;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class TestSolr {
	public static void main(String[] args) throws SolrServerException, IOException {

		CloudSolrServer cloudSolrServer = new CloudSolrServer("10.21.16.53:30001,10.21.16.53:30002");
		cloudSolrServer.setDefaultCollection("co_audit_std");
		cloudSolrServer.setZkClientTimeout(10000);
		cloudSolrServer.setZkConnectTimeout(10000);
		// cloudSolrServer.connect();
		SolrQuery parameters = new SolrQuery();
		String mQueryString = "*:*";
		parameters.setStart(0);
		parameters.setRows(100);
		parameters.set("q", mQueryString);
		QueryResponse response = cloudSolrServer.query(parameters);
		SolrDocumentList list = response.getResults();
		for (SolrDocument docs : list) {
			System.out.println(docs.getFieldValue("pracct_name"));
		}
		cloudSolrServer.commit();
	}
}
