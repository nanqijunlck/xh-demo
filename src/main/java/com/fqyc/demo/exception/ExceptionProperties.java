package com.fqyc.demo.exception;//package com.fqyc.quality.exception;
//
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConfigurationProperties(prefix="exception")
//public class ExceptionProperties
//{
//  private String tipMessage;
//  private String eidTipFormat;
//
//  public String toString()
//  {
//    return "ExceptionProperties(tipMessage=" + getTipMessage() + ", eidTipFormat=" + getEidTipFormat() + ")";
//  }
//
//  public void setTipMessage(String tipMessage)
//  {
//    this.tipMessage = tipMessage;
//  }
//
//  public void setEidTipFormat(String eidTipFormat)
//  {
//    this.eidTipFormat = eidTipFormat;
//  }
//
//  public boolean equals(Object o)
//  {
//    if (o == this) {
//      return true;
//    }
//    if (!(o instanceof ExceptionProperties)) {
//      return false;
//    }
//    ExceptionProperties other = (ExceptionProperties)o;
//    if (!other.canEqual(this)) {
//      return false;
//    }
//    Object this$tipMessage = getTipMessage();Object other$tipMessage = other.getTipMessage();
//    if (this$tipMessage == null ? other$tipMessage != null : !this$tipMessage.equals(other$tipMessage)) {
//      return false;
//    }
//    Object this$eidTipFormat = getEidTipFormat();Object other$eidTipFormat = other.getEidTipFormat();return this$eidTipFormat == null ? other$eidTipFormat == null : this$eidTipFormat.equals(other$eidTipFormat);
//  }
//
//  protected boolean canEqual(Object other)
//  {
//    return other instanceof ExceptionProperties;
//  }
//
//  public int hashCode()
//  {
//    int PRIME = 59;int result = 1;Object $tipMessage = getTipMessage();result = result * 59 + ($tipMessage == null ? 43 : $tipMessage.hashCode());Object $eidTipFormat = getEidTipFormat();result = result * 59 + ($eidTipFormat == null ? 43 : $eidTipFormat.hashCode());return result;
//  }
//
//  public String getTipMessage()
//  {
//    return this.tipMessage;
//  }
//
//  public String getEidTipFormat()
//  {
//    return this.eidTipFormat;
//  }
//}