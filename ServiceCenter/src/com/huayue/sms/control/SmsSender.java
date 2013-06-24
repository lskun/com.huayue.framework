package com.huayue.sms.control;

import javax.swing.*;
import java.awt.*;
import javax.comm.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

/**
 * <p>Title: short messaage sender and monitor.</p>
 * <p>Description: you can use this software to send short message directly,
 * and it can monitor and send short message in remote database
 * submted from web.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: school of computer, wuhan university</p>
 * @author: zhufubao.
 * Email: fbzhu@whu.edu.cn
 * @version 1.0
 */

public class SmsSender
    extends JFrame
    implements SerialPortEventListener, CommPortOwnershipListener {
  //JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel5 = new JLabel();
  JComboBox jComboBox1 = new JComboBox();
  JLabel jLabel6 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JPanel jPanel3 = new JPanel();
  JLabel jLabel7 = new JLabel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JLabel jLabel8 = new JLabel();
  BorderLayout borderLayout3 = new BorderLayout();
  JLabel jLabel9 = new JLabel();
  JPanel jPanel5 = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  JButton jButton4 = new JButton();
  JLabel jLabel10 = new JLabel();
  JPanel jPanel6 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel7 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  CommPortIdentifier portId;
  SerialPort sPort;
  boolean isOpen;
  String sPortName;
  static OutputStream smsout;
  static InputStream smsin;
  JTextPane jTextPane1 = new JTextPane();
  JButton jButton5 = new JButton();
  smsThread st = null;
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenu1 = new JMenu();
  JMenu jMenu2 = new JMenu();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenuItem jMenuItem2 = new JMenuItem();
  JMenuItem jMenuItem3 = new JMenuItem();
  JMenu jMenu3 = new JMenu();
  JMenuItem jMenuItem4 = new JMenuItem();
  JMenuItem jMenuItem5 = new JMenuItem();
  JMenuItem jMenuItem6 = new JMenuItem();
  JMenuItem jMenuItem7 = new JMenuItem();
  String curSmsIndex = "00"; //当前SIM卡上收到的短信总数

  public SmsSender() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    listPortChoices();
  }

  public static void main(String[] args) {
    SmsSender smsSender = new SmsSender();
    smsSender.setTitle("sms sender");
    smsSender.setSize(400, 300);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = smsSender.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    smsSender.setLocation( (screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
    smsSender.setVisible(true);
  }

  private void jbInit() throws Exception {
    jLabel3.setToolTipText("");
    jLabel3.setText("武汉因科 (C) 2005");
    jLabel4.setText("      ");
    jPanel1.setLayout(borderLayout1);
    jLabel5.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel5.setText("端口号");
    jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel6.setText("电话号码");
    jLabel7.setRequestFocusEnabled(true);
    jLabel7.setText("  ");
    jPanel3.setLayout(borderLayout2);
    jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel8.setText("短信内容");
    jPanel4.setLayout(borderLayout3);
    jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel9.setText("已录入了");
    jButton1.setText("连接");
    jButton1.addActionListener(new SmsSender_jButton1_actionAdapter(this));
    jButton2.setEnabled(false);
    jButton2.setText("断开");
    jButton2.addActionListener(new SmsSender_jButton2_actionAdapter(this));
    jButton3.setEnabled(false);
    jButton3.setRequestFocusEnabled(true);
    jButton3.setText("发送");
    jButton3.addActionListener(new SmsSender_jButton3_actionAdapter(this));
    jButton4.setEnabled(false);
    jButton4.setText("监控");
    jButton4.addActionListener(new SmsSender_jButton4_actionAdapter(this));
    jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel10.setText("0 字");
    jPanel2.setLayout(borderLayout4);
    jPanel7.setDebugGraphicsOptions(0);
    jPanel7.setLayout(borderLayout5);
    jTextField1.setBorder(BorderFactory.createEtchedBorder());
    jTextField1.setToolTipText("号码之间以逗号隔开");
    jTextField1.setText("");
    jTextField1.addKeyListener(new SmsSender_jTextField1_keyAdapter(this));
    jTextPane1.setBorder(BorderFactory.createEtchedBorder());
    jTextPane1.setToolTipText("短信内容不能超过70字");
    jTextPane1.setText("");
    jTextPane1.addKeyListener(new SmsSender_jTextPane1_keyAdapter(this));
    jButton5.setEnabled(false);
    jButton5.setText("关控");
    jButton5.addActionListener(new SmsSender_jButton5_actionAdapter(this));
    jMenu1.setText("串口操作");
    jMenu2.setText("短信操作");
    jMenuItem1.setText("连接");
    jMenuItem1.addActionListener(new SmsSender_jMenuItem1_actionAdapter(this));
    jMenuItem2.setEnabled(false);
    jMenuItem2.setText("断开");
    jMenuItem2.addActionListener(new SmsSender_jMenuItem2_actionAdapter(this));
    jMenuItem3.setEnabled(false);
    jMenuItem3.setText("发送短信");
    jMenuItem3.addActionListener(new SmsSender_jMenuItem3_actionAdapter(this));
    jMenu3.setText("远程监控");
    jMenuItem4.setEnabled(false);
    jMenuItem4.setDoubleBuffered(false);
    jMenuItem4.setText("显示所有短信");
    jMenuItem4.addActionListener(new SmsSender_jMenuItem4_actionAdapter(this));
    jMenuItem5.setEnabled(false);
    jMenuItem5.setText("清除所有短信");
    jMenuItem5.addActionListener(new SmsSender_jMenuItem5_actionAdapter(this));
    jMenuItem6.setEnabled(false);
    jMenuItem6.setText("开始监控");
    jMenuItem6.addActionListener(new SmsSender_jMenuItem6_actionAdapter(this));
    jMenuItem7.setEnabled(false);
    jMenuItem7.setText("停止监控");
    jMenuItem7.addActionListener(new SmsSender_jMenuItem7_actionAdapter(this));
    this.getContentPane().add(jMenuBar1, BorderLayout.NORTH);
    this.getContentPane().add(jLabel2, BorderLayout.WEST);
    this.getContentPane().add(jLabel3, BorderLayout.SOUTH);
    this.getContentPane().add(jLabel4, BorderLayout.EAST);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel2, BorderLayout.NORTH);
    jPanel2.add(jPanel6, BorderLayout.WEST);
    jPanel6.add(jLabel5, null);
    jPanel6.add(jComboBox1, null);
    jPanel2.add(jPanel7, BorderLayout.CENTER);
    jPanel7.add(jLabel6, BorderLayout.WEST);
    jPanel7.add(jTextField1, BorderLayout.CENTER);
    jPanel1.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jLabel7, BorderLayout.NORTH);
    jPanel3.add(jPanel4, BorderLayout.WEST);
    jPanel4.add(jLabel8, BorderLayout.NORTH);
    jPanel4.add(jLabel9, BorderLayout.CENTER);
    jPanel4.add(jLabel10, BorderLayout.SOUTH);
    jPanel3.add(jPanel5, BorderLayout.SOUTH);
    jPanel5.add(jButton1, null);
    jPanel5.add(jButton2, null);
    jPanel5.add(jButton3, null);
    jPanel5.add(jButton4, null);
    jPanel5.add(jButton5, null);
    jPanel3.add(jTextPane1, BorderLayout.CENTER);
    jMenuBar1.add(jMenu1);
    jMenuBar1.add(jMenu2);
    jMenuBar1.add(jMenu3);
    jMenu1.add(jMenuItem1);
    jMenu1.add(jMenuItem2);
    jMenu2.add(jMenuItem3);
    jMenu2.add(jMenuItem4);
    jMenu2.add(jMenuItem5);
    jMenu3.add(jMenuItem6);
    jMenu3.add(jMenuItem7);
  }

  void listPortChoices() {
    CommPortIdentifier portId;
    Enumeration en = CommPortIdentifier.getPortIdentifiers();
    while (en.hasMoreElements()) {
      portId = (CommPortIdentifier) en.nextElement();
      if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
        jComboBox1.addItem(portId.getName());
      }
    }
    //jComboBox1.select(parameters.getPortName());
  }

  void jButton1_actionPerformed(ActionEvent e) {
    try {
      portId = CommPortIdentifier.getPortIdentifier( (String) jComboBox1.
          getSelectedItem());
    }
    catch (NoSuchPortException e1) {
      JOptionPane.showMessageDialog(null, e1.getMessage(), "error", 1);
    }
    try {
      sPort = (SerialPort) portId.open("SmsSender", 30000);
    }
    catch (PortInUseException e1) {
      JOptionPane.showMessageDialog(null, e1.getMessage(), "error", 1);
    }
    try {
      sPort.setSerialPortParams(115200, SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
      sPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
    }
    catch (UnsupportedCommOperationException e1) {
      sPort.close();
      JOptionPane.showMessageDialog(null, e1.getMessage(), "error", 1);
    }
    try {
      smsout = sPort.getOutputStream();
      smsin = sPort.getInputStream();
    }
    catch (IOException e1) {
      sPort.close();
      JOptionPane.showMessageDialog(null, e1.getMessage(), "error", 1);
    }

    // Add this object as an event listener for the serial port.
    try {
      sPort.addEventListener(this);
    }
    catch (TooManyListenersException e1) {
      sPort.close();
      JOptionPane.showMessageDialog(null, e1.getMessage(), "error", 1);
    }

    // Set notifyOnDataAvailable to true to allow event driven input.
    sPort.notifyOnDataAvailable(true);

    // Set notifyOnBreakInterrup to allow event driven break handling.
    sPort.notifyOnBreakInterrupt(true);

    // Set receive timeout to allow breaking out of polling loop during
    // input handling.
    try {
      sPort.enableReceiveTimeout(30);
    }
    catch (UnsupportedCommOperationException e1) {
    }

    // Add ownership listener to allow ownership event handling.
    portId.addPortOwnershipListener(this);

    jButton1.setEnabled(false);
    jButton2.setEnabled(true);
    jButton3.setEnabled(true);
    jButton4.setEnabled(true);
    jMenuItem1.setEnabled(false);
    jMenuItem2.setEnabled(true);
    jMenuItem3.setEnabled(true);
    jMenuItem4.setEnabled(true);
    jMenuItem5.setEnabled(true);
    jMenuItem6.setEnabled(true);
    isOpen = true;
    jComboBox1.setEnabled(false);
    try {
      smsout.write("AT+CMGF=0\r".getBytes());
      Thread.sleep(1000);
      smsout.write("AT+CNMI=2,1\r".getBytes());
    }
    catch (Exception e1) {
      JOptionPane.showMessageDialog(this, e1.getMessage(), "error", 1);
    }
  }

  void jButton2_actionPerformed(ActionEvent e) {
    jButton1.setEnabled(true);
    jButton2.setEnabled(false);
    jButton3.setEnabled(false);
    jButton4.setEnabled(false);
    jButton5.setEnabled(false);
    jMenuItem1.setEnabled(true);
    jMenuItem2.setEnabled(false);
    jMenuItem3.setEnabled(false);
    jMenuItem4.setEnabled(false);
    jMenuItem5.setEnabled(false);
    jMenuItem7.setEnabled(false);

    jComboBox1.setEnabled(true);
    if (sPort != null) {
      sPort.close();
    }
    isOpen = false;
  }

  void jButton3_actionPerformed(ActionEvent e) {
    System.out.println("正在发送短信...");
    String dialNums = jTextField1.getText().trim();
    String dialText = jTextPane1.getText().trim();
    String dialNum;
    int dialTextLen = getDialTextHex(dialText).length() / 2;
    String dialTextLenHex = Integer.toHexString(dialTextLen);
    if (dialTextLen < 15) {
      dialTextLenHex = "0" + dialTextLenHex;
    }
    String dialNumAr[] = dialNums.split(",");
    for (int i = 0; i < dialNumAr.length; i++) {
      dialNum = dialNumAr[i];
      if (isDialNumValid(dialNum)) {
        try {
          //smsout.write("AT+CMGF=0\r".getBytes());
          String cmd0 = "AT+CMGS=" + (dialTextLen + 15) + "\r";
          smsout.write(cmd0.getBytes());
          String cmd1 = "0011000D9168" + getDialNumHex(dialNum) + "0008A7" +
              dialTextLenHex + getDialTextHex(dialText) + (char) 26;
          smsout.write(cmd1.getBytes());

          System.out.println("正在向 " + dialNum + " 发短信...");
        }
        catch (Exception e1) {
          JOptionPane.showMessageDialog(this, e1.getMessage(), "error", 1);
        }
        try {
          Thread.sleep(5000);
        }
        catch (Exception e1) {
          JOptionPane.showMessageDialog(this, e1.getMessage(), "error", 1);
        }
      }
      else {
        System.out.println("手机号 " + dialNum + " 不合法...");
      }
    }
    System.out.println("发送完毕，谢谢使用...");
  }

  public String getDialNumHex(String dialNum) {
    String dialNumHex = "";
    int b = dialNum.length();
    char c1, c2;
    for (int ib = 2; ib < b; ib++) {
      if (ib % 2 == 0) {
        String c = dialNum.substring(ib - 2, ib);
        c1 = c.charAt(0);
        c2 = c.charAt(1);
        dialNumHex = dialNumHex + c2 + c1;
      }
    }
    dialNumHex = dialNumHex + 'F' + dialNum.substring(b - 1, b);
    return dialNumHex;
  }

  public String getDialTextHex(String dialText) {
    String dialTextHex = "";
    int b = dialText.length();
    char ch;
    for (int i = 0; i < b; i++) {
      ch = dialText.charAt(i);
      int chUc = new Character(ch).hashCode();
      String chUcHex = Integer.toHexString(chUc);
      if (chUcHex.length() < 2) {
        chUcHex = "000" + chUcHex;
      }
      if (chUcHex.length() < 3) {
        chUcHex = "00" + chUcHex;
      }
      if (chUcHex.length() < 4) {
        chUcHex = "0" + chUcHex;
      }
      dialTextHex = dialTextHex + chUcHex;
    }
    return dialTextHex;
  }

  public boolean isDialNumValid(String dialNum) {
    if (dialNum == null || dialNum.length() != 11) {
      return false;
    }
    String dialNumL = "13000000000";
    String dialNumR = "13999999999";
    int c1 = dialNum.compareTo(dialNumL);
    int c2 = dialNumR.compareTo(dialNum);
    if (c1 * c2 > 0) {
      return true;
    }
    else {
      return false;
    }
  }

  public void serialEvent(SerialPortEvent e) {
    StringBuffer inputBuffer = new StringBuffer();
    int newData = 0;
    switch (e.getEventType()) {
      case SerialPortEvent.DATA_AVAILABLE:
        while (newData != -1) {
          try {
            newData = smsin.read();
            if (newData == -1) {
              break;
            }
            if ('\r' == (char) newData) {
              inputBuffer.append('\n');
            }
            else {
              inputBuffer.append( (char) newData);
            }
          }
          catch (IOException ex) {
            System.err.println(ex);
            return;
          }
        }
        String receivedStr = new String(inputBuffer);
        //System.out.println(receivedStr);
        String x = receivedStr.replace('\n', ' ');
        receivedStr = x.trim();
        System.out.println(receivedStr);
        if (receivedStr.startsWith("+CMTI:")) {
          curSmsIndex = receivedStr.substring(receivedStr.lastIndexOf(",") +
                                              1, receivedStr.length());
          try {
            smsout.write( ("AT+CMGR=" + curSmsIndex + "\r").getBytes());
          }
          catch (Exception e1) {}
        }
        if (receivedStr.startsWith("+CMGR:")) {
          int indexOfSms = receivedStr.indexOf("  ");
          String str = receivedStr.substring(indexOfSms + 2, receivedStr.length());
          String sTelNum = "";
          String sTelTime = "";
          String sTelContent = "";
          String sTmp = "";
          int sContentLen = 0;
          int nTelNumBegin = 24;
          int nTelNumEnd = 36 +
              str.substring(36, str.length() - 1).indexOf("0008");
          sTelNum = str.substring(nTelNumBegin, nTelNumEnd);
          sTelTime = str.substring(nTelNumEnd + 4, nTelNumEnd + 16);
          sContentLen = Integer.parseInt(str.substring(nTelNumEnd + 18,
              nTelNumEnd + 20), 16);
          sTelContent = str.substring(nTelNumEnd + 20,
                                      nTelNumEnd + 20 + sContentLen * 2);
          for (int i = 0; i < sTelNum.length(); i = i + 2) { //取得电话号码
            char ch1 = sTelNum.charAt(i);
            char ch2 = sTelNum.charAt(i + 1);
            sTmp = sTmp + ch2 + ch1;
          }
          sTelNum = sTmp;
          if (sTmp.charAt(sTmp.length() - 1) == 'F' ||
              sTmp.charAt(sTmp.length() - 1) == 'f') {
            sTelNum = sTmp.substring(0, sTmp.length() - 1);
          }
          sTmp = "";
          for (int i = 0; i < sTelTime.length(); i = i + 2) { //取得日期时间
            char ch1 = sTelTime.charAt(i);
            char ch2 = sTelTime.charAt(i + 1);
            sTmp = sTmp + ch2 + ch1;
            switch (i) {
              case 0:
              case 2:
                sTmp += '-';
                break;
              case 4:
                sTmp += ' ';
                break;
              case 6:
              case 8:
                sTmp += ':';
                break;
            }
          }
          sTelTime = sTmp;
          sTmp = "";
          for (int i = 0; i < sTelContent.length(); i = i + 4) { //取得内容
            sTmp = sTmp +
                (char) Integer.parseInt(sTelContent.substring(i, i + 4), 16);
          }
          System.out.println("主叫号码: " + sTelNum + "\t" + "呼叫时间: 20" + sTelTime);
          System.out.println("呼叫内容\n" + sTmp);

        }
        break;
      case SerialPortEvent.BI:
        System.out.println("\n--- BREAK RECEIVED ---\n");
    }
  }

  public void ownershipChange(int type) {
    if (type == CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED) {
      JOptionPane.showMessageDialog(this, "Do you want to give up your port?",
                                    "error", 1);
    }
  }

  void jTextPane1_keyReleased(KeyEvent e) {
    String txt = jTextPane1.getText();
    if (txt.length() > 70) {
      JOptionPane.showMessageDialog(this, "不能超过70字", "对不起", 1);
      jTextPane1.setText(txt.substring(0, 70));
    }
    jLabel10.setText(jTextPane1.getText().length() + " 字");
  }

  void jTextField1_keyPressed(KeyEvent e) {
    String txt = jTextField1.getText();
    if (txt.length() > 0) {
      char ch = txt.charAt(txt.length() - 1);
      if (! (ch >= '0' && ch < '9' || ch == ',')) {
        jTextField1.setText(txt.substring(0, txt.length() - 1));
      }
    }
  }

  void jTextField1_keyReleased(KeyEvent e) {
    String txt = jTextField1.getText();
    if (txt.length() > 0) {
      char ch = txt.charAt(txt.length() - 1);
      if (! (ch >= '0' && ch < '9' || ch == ',')) {
        jTextField1.setText(txt.substring(0, txt.length() - 1));
      }
    }
  }

  void jButton4_actionPerformed(ActionEvent e) {
    jButton4.setEnabled(false);
    jButton5.setEnabled(true);
    jMenuItem6.setEnabled(false);
    jMenuItem7.setEnabled(true);
    st = new smsThread(this);
    st.start();
  }

  void jButton5_actionPerformed(ActionEvent e) {
    st.interrupt();
    st = null;
    jButton4.setEnabled(true);
    jButton5.setEnabled(false);
    jMenuItem6.setEnabled(true);
    jMenuItem7.setEnabled(false);
  }

  void jMenuItem1_actionPerformed(ActionEvent e) {
    jButton1_actionPerformed(e);
  }

  void jMenuItem2_actionPerformed(ActionEvent e) {
    jButton2_actionPerformed(e);
  }

  void jMenuItem3_actionPerformed(ActionEvent e) {
    jButton3_actionPerformed(e);
  }

  void jMenuItem4_actionPerformed(ActionEvent e) {
    int smsTotal = Integer.parseInt(curSmsIndex, 10);
    if (smsTotal == 0) {
      smsTotal = 20;
    }
    for (int i = 1; i < smsTotal + 1; i++) {
      String listAll = "AT+CMGR=" + i + "\r";
      try {
        smsout.write(listAll.getBytes());
        Thread.sleep(1000);
      }
      catch (Exception e1) {
        JOptionPane.showMessageDialog(this, e1.getMessage(), "error", 1);
      }
    }
  }

  void jMenuItem5_actionPerformed(ActionEvent e) {
    int smsTotal = Integer.parseInt(curSmsIndex, 10);
    if (smsTotal == 0) {
      smsTotal = 20;
    }
    for (int i = 1; i < smsTotal + 1; i++) {
      String delAll = "AT+CMGD=" + i + "\r";
      try {
        smsout.write(delAll.getBytes());
        Thread.sleep(1000);
      }
      catch (Exception e1) {
        JOptionPane.showMessageDialog(this, e1.getMessage(), "error", 1);
      }
    }
  }

  void jMenuItem6_actionPerformed(ActionEvent e) {
    jButton4_actionPerformed(e);
  }

  void jMenuItem7_actionPerformed(ActionEvent e) {
    jButton5_actionPerformed(e);
  }
}

class SmsSender_jButton1_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jButton1_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class SmsSender_jButton2_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jButton2_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class SmsSender_jButton3_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jButton3_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class SmsSender_jTextPane1_keyAdapter
    extends java.awt.event.KeyAdapter {
  SmsSender adaptee;

  SmsSender_jTextPane1_keyAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void keyReleased(KeyEvent e) {
    adaptee.jTextPane1_keyReleased(e);
  }
}

class SmsSender_jTextField1_keyAdapter
    extends java.awt.event.KeyAdapter {
  SmsSender adaptee;

  SmsSender_jTextField1_keyAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.jTextField1_keyPressed(e);
  }

  public void keyReleased(KeyEvent e) {
    adaptee.jTextField1_keyReleased(e);
  }
}

class SmsSender_jButton4_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jButton4_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}

class SmsSender_jButton5_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jButton5_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}

class smsThread
    extends Thread {
  SmsSender ss;
  public smsThread(SmsSender parent) {
    ss = parent;
  }

  public void run() {
    while (true) {
      System.out.println("-------" +
                         new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                         format(new java.util.Date()) + ", 扫描数据库...-----");
      DbConnect dbConnect = new DbConnect();
      dbConnect.openConnection();
      String sql = "select * from shortmsg where nStatus = 1";
      try {
        ResultSet rs = dbConnect.executeQuery(sql);
        if (rs.next()) {
          int nMsgID = rs.getInt(1);
          String dialNums = rs.getString("sMobile").trim();
          String dialText = rs.getString("sMsgContent").trim();
          rs.close();
          dialText = dialText.substring(0, 69);
          System.out.println("手机号码: " + dialNums);
          System.out.println("短信内容: " + dialText);
          sql = "update shortmsg set nStatus = 2 where nMsgID=" + "'" + nMsgID +
              "'";
          dbConnect.executeUpdate(sql);
          dbConnect.close();
          String dialNum;
          int dialTextLen = ss.getDialTextHex(dialText).length() / 2;
          String dialTextLenHex = Integer.toHexString(dialTextLen);
          if (dialTextLen < 15) {
            dialTextLenHex = "0" + dialTextLenHex;
          }
          String dialNumAr[] = dialNums.split(",");
          for (int i = 0; i < dialNumAr.length; i++) {
            dialNum = dialNumAr[i];
            if (ss.isDialNumValid(dialNum)) {
              try {
                String cmd0 = "AT+CMGS=" + (dialTextLen + 15) + "\r";
                ss.smsout.write(cmd0.getBytes());
                String cmd1 = "0011000D9168" + ss.getDialNumHex(dialNum) +
                    "0008A7" + dialTextLenHex + ss.getDialTextHex(dialText) +
                    (char) 26;
                ss.smsout.write(cmd1.getBytes());
                System.out.println("正在向 " + dialNum + " 发短信...");
              }
              catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "error", 1);
              }
              try {
                Thread.sleep(5000);
              }
              catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "error", 1);
              }
            }
            else {
              System.out.println("手机号 " + dialNum + " 不合法...");
            }
          }
          System.out.println("发送完毕，谢谢使用...");
        }
      }
      catch (SQLException e) {
        System.out.println(e.getMessage());
      }
      try {
        this.sleep(5000);
      }
      catch (Exception e) {
        return;
      }
    }
  }
}

class DbConnect {

  Connection conn = null;
  Statement stmt = null;
  ResultSet rset = null;

  public DbConnect() {
  }

  public static void main(String args[]) {
    DbConnect dbconnect = new DbConnect();
    System.out.println(dbconnect.openConnection());
  }

  public boolean openConnection() {
    Properties properties = new Properties();
    try {
      InputStream inputstream = new FileInputStream(System.getProperty(
          "user.dir") + "\\dbgb2312.txt");
      properties.load(inputstream);
      if (inputstream != null) {
        inputstream.close();
      }
    }
    catch (IOException ioexception) {
      System.out.println("[DbConnection] Open db.txt File, Error!");
    }
    String driver = properties.getProperty("driver");
    String conparam = properties.getProperty("url");
    try {
      Class.forName(driver).newInstance();
    }
    catch (ClassNotFoundException classnotfoundexception) {
      System.out.println("JDBC login, Error!@" +
                         classnotfoundexception.getMessage());
      return false;
    }
    catch (Exception exception) {
      System.err.println("Unable to load driver!");
      exception.printStackTrace();
    }
    try {
      conn = DriverManager.getConnection(conparam);
    }
    catch (SQLException sqlexception) {
      System.out.println("Generate Connection, Error!" +
                         sqlexception.getMessage());
      return false;
    }
    return true;
  }

  public Connection getConn() {
    return conn;
  }

  public ResultSet executeQuery(String sql) throws SQLException {
    stmt = conn.createStatement(1005, 1008);
    rset = stmt.executeQuery(sql);
    return rset;
  }

  public void executeUpdate(String sql) throws SQLException {
    stmt = conn.createStatement(1005, 1008);
    stmt.executeUpdate(sql);
    if (stmt != null) {
      stmt.close();
    }
  }

  public void close() throws SQLException {
    if (conn != null) {
      conn.close();
    }
    if (rset != null) {
      rset.close();
    }
    if (stmt != null) {
      stmt.close();
    }
  }
}

class SmsSender_jMenuItem1_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jMenuItem1_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem1_actionPerformed(e);
  }
}

class SmsSender_jMenuItem2_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jMenuItem2_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem2_actionPerformed(e);
  }
}

class SmsSender_jMenuItem3_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jMenuItem3_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem3_actionPerformed(e);
  }
}

class SmsSender_jMenuItem4_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jMenuItem4_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem4_actionPerformed(e);
  }
}

class SmsSender_jMenuItem5_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jMenuItem5_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem5_actionPerformed(e);
  }
}

class SmsSender_jMenuItem6_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jMenuItem6_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem6_actionPerformed(e);
  }
}

class SmsSender_jMenuItem7_actionAdapter
    implements java.awt.event.ActionListener {
  SmsSender adaptee;

  SmsSender_jMenuItem7_actionAdapter(SmsSender adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem7_actionPerformed(e);
  }
}
