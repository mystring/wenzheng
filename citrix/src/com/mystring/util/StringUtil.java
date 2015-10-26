package com.mystring.util;

public class StringUtil
{
  public static boolean isBlank(String st)
  {
    return ((st == null) || (st.trim().equals("")));
  }
}