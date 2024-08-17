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
<title>ショッピングサイトLatte買い物カゴの中身</title>
</head>
<body id="cart_page">
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
			<h3>買い物カゴの中身</h3>
			<p>現在買い物カゴには以下の商品が入っています。</p>
			<table border="1" summary="カート一覧">
				<tr class="tab_cart_head">
					<th class="tab_cart_width">商品名</th>
					<th class="tab_cart_width">メーカー等</th>
					<th class="tab_cart_width">価格</th>
					<th>
						&nbsp;
					</th>
				</tr>

				<!-- ●！！！！表の作成はここから！！！！●  -->
				<%
				ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

				if (cart != null) {
					for (int i = 0; i < cart.size(); i++) {
						Item item = cart.get(i);
				%>
				<form action="kenshop" method="post">
					<div class="items">
						<tr>
							<td><div class="lineup"><%=item.getItemName()%></div></td>
							<td><div class="lineup"><%=item.getItemArtist()%></div></td>
							<td>
								<div class="lineup"><%=item.getItemPrice()%></div>
							</td>
							<td><div class="lineup_btn">
									<input type="submit" value="取り消し"
										style="background-color: #999999; font-size: 9pt; color: #ffffff" />
									<input type="hidden" name="act" value="remove" /> <input
										type="hidden" name="remove" value="<%=i%>" />
								</div></td>
						</tr>

					</div>
				</form>
				<%
				}
				}
				%>
			</table>
			<!-- ↑↑↑↑表の作成はここまで↑↑↑↑  -->
			<form action="kenshop" method="post">
				<p>
					上記の内容で注文画面へ進む場合はこちら<br /> <input type="image"
						src="img/btn_confirmation.gif" alt="戻る" /> <input type="hidden"
						name="act" value="for_order" />
				</p>
			</form>
			<p>
				まだ買い物を続けたいので検索画面に戻る場合はこちら<br /> <a href="kenshop">検索画面に戻る</a>
			</p>
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