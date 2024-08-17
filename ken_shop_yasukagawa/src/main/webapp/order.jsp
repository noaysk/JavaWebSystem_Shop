<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  page import="java.util.ArrayList, ken.bean.Item"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="content-style-type" content="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css"
	media="screen" />
<title>ショッピングサイトLatte送付先登録画面</title>
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<script type="text/javascript">
<!--
	function check() {
		for (i = 0; i < document.forms[0].elements.length; i++) {
			if (document.forms[0].elements[i].value == "") {
				window.alert("入力されていない項目があります。");
				return false;
			}
		}
		return true;
	}
//-->
</script>
</head>
<body id="order_page">
	<div id="wrap">
		<div id="header">
			<h1>
				<img src="img/site_id_new.png" width="204" height="120" alt="logo" />
			</h1>
		</div>
		<hr />
		<div id="nav">
			<ul>
				<li><a href="#">ホーム</a></li>
				<li><a href="#">ショップガイド</a></li>
				<li><a href="#">よくある質問</a></li>
				<li><a href="#">会社案内</a></li>
				<li><a href="#">お問い合わせ</a></li>
			</ul>
		</div>
		<div id="visual">
			<h2>
				<img src="img/yokohama01.jpg" alt="Latteトップページ" width="900"
					height="200" />
			</h2>
		</div>
		<div id="main_contents">
			<h3>送付先登録画面</h3>
			<p>買い物の内容は以下になります</p>
			<table summary="リスト">
				<tr>
					<th class="tab_cart_width">タイトル</th>
					<th class="tab_cart_width">メーカー等</th>
					<th class="tab_cart_width">価格</th>
				</tr>
				<!-- ●！！！！表の作成はここから！！！！●  -->
				<%
				ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

				int sum = 0;

				if (cart != null) {
					for (int i = 0; i < cart.size(); i++) {
						Item item = cart.get(i);
						sum += item.getItemPrice();
				%>
				<tr>
					<td><%=item.getItemName()%></td>
					<td><%=item.getItemArtist()%></td>
					<td><%=item.getItemPrice()%></td>
				</tr>
				<%
				}
				}
				%>
				<!-- ↑↑↑↑表の作成はここまで↑↑↑↑  -->
			</table>
			<p>
				<strong>合計金額は税込みで 円になります。</strong>
			</p>
			<p>
				下のフォームにお客様のお名前、ご住所、電話番号、メールアドレスを入力して、<br />
				よろしければ「注文する」ボタンを押してください。<br /> 項目は必ず入力してください。機種依存文字は入力できません。
			</p>
			<form action="kenshop" method="post" onsubmit="return check();">
				<table summary="個人情報登録">
					<tr>
						<td class="td_order_field">お名前</td>
						<td><input type="text" name="name" value=""
							style="width: 185px" /> <br />（例）山田太郎</td>
					</tr>
					<tr>
						<td class="td_order_field">フリガナ</td>
						<td><input type="text" name="kana_name" value=""
							style="width: 185px" /> <br />（例）ヤマダタロウ</td>
					</tr>
					<tr>
						<td class="td_order_field">郵便番号</td>
						<td><input type="text" name="postcode" size="8" maxlength="8"
							value="" /> （郵便番号検索は <a
							href="http://www.post.japanpost.jp/zipcode/index.html"
							target="_blank"> 検索 </a>） <br />（例）153-0052 <br /> ※
							国外の方は000-0000 と入力してください。</td>
					</tr>
					<tr>
						<td class="td_order_field">住所</td>
						<td><input type="text" name="address" value=""
							style="width: 370px" /> <br />（例）東京都世田谷区北沢1-1-1KENコーポ203号室</td>
					</tr>
					<tr>
						<td class="td_order_field">電話番号</td>
						<td><input type="text" name="tel" size="13" maxlength="13"
							value="" /> <br />（例）03-0000-0000</td>
					</tr>
					<tr>
						<td class="td_order_field">メールアドレス</td>
						<td><input type="text" name="mail" value=""
							style="width: 370px" /> <br />（例）hogehoge@kenschool.jp</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><strong>上記の内容で問題なければ、注文を確定いたします</strong>
							<br /> <input type="image" src="img/btn_order.gif" alt="注文"
							onmouseover="document.body.style.cursor='pointer'"
							onmouseout="document.body.style.cursor='default'" /> <input
							type="hidden" name="act" value="order" /></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><a href="kenshop?act=check">ショッピングカートに戻る</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<hr />
		<div id="sub_contents">
			<h4>
				<img src="img/sidemn_shopguide.gif" width="96" height="15"
					alt="ショッピングガイド" />
			</h4>
			<ul>
				<li><a href="#">ご購入方法</a></li>
				<li><a href="#">お支払い方法</a></li>
				<li><a href="#">配送料</a></li>
				<li><a href="#">ラッピング</a></li>
				<li><a href="#">返品・交換</a></li>
			</ul>
			<p>
				全国無料配送<br /> 各種クレジットカードもご利用になれます。
			</p>
			<p>販売元：株式会社シンクスバンク</p>
		</div>
		<hr />
		<div id="footer">
			<p>
				<a href="#wrap"> <img src="img/page_top.gif" width="49"
					height="9" alt="ページトップ" />
				</a>
			</p>
			<address>
				Copyright
				&copy;
				2012-2014 Thinketh Bank Co., Ltd. All Rights Reserved.
			</address>
		</div>
	</div>
</body>
</html>