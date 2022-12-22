package core.util;


import com.commodityDetails.model.entity.CommodityDetails;
import com.commodityDetails.model.service.CommodityDetailsService;
import com.commodityDetails.model.service.impl.CommodityDetailsServiceImpl;
import com.orderBuy.model.entity.OrderBuy;
import com.orderBuy.model.service.OrderBuyService;
import com.orderBuy.model.service.impl.OrderBuyServiceImpl;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.*;

public class MailServiceForOrder {

    // 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
    public void sendMail(String to, String subject, String PaymentDate, String memName, String memId, String orderId, String finalPrice) {

        try {
            // 設定使用SSL連線至 Gmail smtp Server
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
            // ●1) 登入你的Gmail的:
            // ●2) 點選【管理你的 Google 帳戶】
            // ●3) 點選左側的【安全性】

            // ●4) 完成【兩步驟驗證】的所有要求如下:
            //     ●4-1) (請自行依照步驟要求操作之.....)

            // ●5) 完成【應用程式密碼】的所有要求如下:
            //     ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
            //     ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
            //     ●5-3) 最後按【產生】密碼
            final String myGmail = "lichengxu053@gmail.com";
            final String myGmail_password = "xpyffakxaiodvkyi";
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myGmail, myGmail_password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myGmail));
            Transport transport = session.getTransport();

            //設定信中的主旨
            message.setSubject(subject);
            //設定信中的內容
            MimeBodyPart textPart = new MimeBodyPart();
            StringBuffer html = new StringBuffer();
            CommodityDetailsService commodityDetailsService = new CommodityDetailsServiceImpl();
            List<CommodityDetails> list = commodityDetailsService.listOrderDetails(Integer.valueOf(orderId));

            html.append(
                    "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" bgcolor=\"#eaeeef\" leftmargin=\"0\">\r\n"
                            + "    <!--100% body table-->\r\n"
                            + "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\" style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\r\n"
                            + "        <tr>\r\n" + "            <td>\r\n"
                            + "                <table style=\"background-color: #f2f3f8; max-width:700px; margin:0 auto;\" width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
                            + "                    <tr>\r\n"
                            + "                        <td style=\"height:80px;\">&nbsp;</td>\r\n"
                            + "                    </tr>\r\n" + "                    <tr>\r\n"
                            + "                        <td style=\"text-align:center;\">\r\n"
                            + "                            <a href=\"#\" title=\"logo\" target=\"_blank\">");
            html.append("<img width=\"200px\" title=\"logo\" alt=\"logo\" src='cid:image'/><br>");
            html.append(" </a>\r\n" + "                        </td>\r\n" + "                    </tr>\r\n"
                    + "                    <tr>\r\n" + "                        <td height=\"20px;\">&nbsp;</td>\r\n"
                    + "                    </tr>\r\n" + "                    <tr>\r\n"
                    + "                        <td>\r\n"
                    + "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px; background:#fff; border-radius:3px; text-align:left;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\r\n"
                    + "                                <tr>\r\n"
                    + "                                    <td style=\"padding:40px;\">\r\n"
                    + "                                        <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
                    + "                                            <tr>\r\n"
                    + "                                                <td>\r\n"
                    + "                                                    <h1 style=\"color: #1e1e2d; font-weight: 500; margin: 0;margin-bottom: 10px; font-size: 32px;font-family:'Rubik',sans-serif;\">Hi" + " " + memName + ",</h1>\r\n"
                    + "                                                    <p style=\"font-size:15px; color:#455056; line-height:10px; margin:8px 0 30px;\">您的會員編號: " + memId + " 訂單編號: " + orderId + "</p>\r\n"
                    + "                                                    <p style=\"font-size:15px; color:#455056; line-height:10px; margin:8px 0 30px;\">付款成功日期: " + PaymentDate + "</p>\r\n"
                    + "                                                    <p style=\"font-size:15px; color:#455056; line-height:10px; margin:8px 0 30px;\">訂單明細: " + "</p>\r\n"
                    + "                                                </td>\r\n"
                    + "                                            </tr>\r\n" + "\r\n" + "\r\n"
                    + "                                            <!-- 表格內容 -->\r\n"
                    + "                                            <tr>\r\n"
                    + "                                                <td>\r\n"
                    + "                                                    <table width=\"100%\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
                    + "                                                        <tr>\r\n"
                    + "                                                            <th>商品編號</th>\r\n"
                    + "                                                            <th>商品名稱</th>\r\n"
                    + "                                                            <th>商品數量</th>\r\n"
                    + "                                                            <th>商品價格</th>\r\n"
                    + "                                                            <th>小計</th>\r\n" + "\r\n" + "\r\n" + "\r\n"
                    + "\r\n" + "                                                        </tr>");
//訂單明細包裝
            for (CommodityDetails commodityDetails : list) {
                html.append("<tr>");
                html.append("<td>" + commodityDetails.getItemId() + "</td>");
                html.append("<td>" + commodityDetails.getItemName() + "</td>");
                html.append("<td>" + commodityDetails.getCdAmount() + "</td>");
                html.append("<td>" + commodityDetails.getItemPrice() + "</td>");
                html.append("<td></td>");
                html.append("</tr>");
            }
//總金額計算
            html.append("<tr>");
            html.append("<td></td>");
            html.append("<td></td>");
            html.append("<td></td>");
            html.append("<td>折扣後金額</td>");
            html.append("<td> NT$ " + finalPrice + "</td>");
            html.append("</tr>");

            html.append("\r\n" + "</td>\r\n" + "</tr>\r\n" + "</table>\r\n"

                    + "                                                    <p style=\"font-size:15px; color:#455056; line-height:10px; margin:8px 0 30px;\">本郵件由系統自動產生，請勿直接回信，如有需求直接聯繫客服人員謝謝! " + "</p>\r\n"
                    + "                                    </td>\r\n" + "                                </tr>\r\n"
                    + "                            </table>\r\n" + "                        </td>\r\n"
                    + "                    </tr>\r\n" + "                    <tr>\r\n"
                    + "                        <td style=\"height:25px;\">&nbsp;</td>\r\n"
                    + "                    </tr>\r\n" + "                    <tr>\r\n"
                    + "                        <td style=\"text-align:center;\">\r\n"
                    + "                            <p style=\"font-size:14px; color:#455056bd; line-height:18px; margin:0 0 0;\">&copy; <strong>Ba-rei 巴蕊 since 2022</strong></p>\r\n"
                    + "                        </td>\r\n" + "                    </tr>\r\n"
                    + "                    <tr>\r\n"
                    + "                        <td style=\"height:80px;\">&nbsp;</td>\r\n"
                    + "                    </tr>\r\n" + "                </table>\r\n" + "            </td>\r\n"
                    + "        </tr>\r\n" + "    </table>\r\n" + "</body>");

            textPart.setContent(html.toString(), "text/html; charset=UTF-8");

            // 圖檔部份，注意 html 用 cid:image，則header要設<image>
            MimeBodyPart picturePart = new MimeBodyPart();
            File f = new File("C:\\CGA104\\CGA104G1\\src\\main\\webapp\\resources\\static\\image\\ba-rei 02.png");
            FileDataSource fds = new FileDataSource(f);
            picturePart.setDataHandler(new DataHandler(fds));
            picturePart.setFileName(fds.getName());
            picturePart.setHeader("Content-ID", "<image>");

            // 準備中
            Multipart email = new MimeMultipart();
            email.addBodyPart(textPart);
            email.addBodyPart(picturePart);

            message.setContent(email);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            transport.connect();
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();

            System.out.println("傳送成功!");
        } catch (MessagingException e) {
            System.out.println("傳送失敗!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//			RequestDispatcher failureView = req.getRequestDispatcher("/frontend/mem/select_page.jsp");
//			failureView.forward(req, res);


        String to = "ritchie4cga104g1@gmail.com";
//        String to = "nbailove3@gmail.com";

        String subject = "TEST";

        MailServiceForOrder mailService = new MailServiceForOrder();

        String memName = "吳永志";

//        mailService.sendMail(to, subject, "2022-12-11", memName, "1", "27", "1000.0");

    }

}
