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
<title>ショッピングサイトLatteトップページ</title>
</head>
<body id="top_page">
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
			<h3>欲しいモノのカテゴリー選択してください</h3>
			<form action="kenshop" method="post">
				<table summary="検索欄">
					<tr>
						<td>分類</td>
						<td><select name="genre">
								<option value="book" selected="selected">書籍</option>
								<option value="cd">CD</option>
								<option value="game">GAME</option>
						</select></td>
						<td>キーワード</td>
						<td><input type="text" name="keyword" size="20" value="" /></td>
						<td><input type="submit" name="search" value="検索"
							class="submit_btn" /> <input type="hidden" name="act"
							value="search" /></td>
					</tr>
				</table>
			</form>

			<%
			ArrayList<Item> table_items = (ArrayList<Item>) session.getAttribute("table_items");
			ArrayList<Item> cart_items = (ArrayList<Item>) session.getAttribute("cart");

			int cart_size = 0;
			if (cart_items != null) {
				cart_size = cart_items.size();
			}
			%>

			<h3>現在選択されている商品</h3>
			<form action="kenshop" method="post">
				<table summary="選択商品">
					<tr>
						<td class="tab_cart_width">選択商品 <%=cart_size--%>個
						</td>
						<td class="tab_cart_width"><input type="image"
							src="img/btn_cart.gif" alt="カートの中身を見る" /> <input type="hidden"
							name="act" value="check" /></td>
					</tr>
				</table>
			</form>
			<h3>選択カテゴリー商品</h3>
			<table border="1" summary="選択カテゴリー商品">
				<tr class="tab_items_head">
					<th class="td_img">商品</th>
					<th>商品名&nbsp; &nbsp;著者&nbsp; &nbsp;価格
					</th>
					<th class="td_btn">
						&nbsp;
					</th>
				</tr>

				<!-- ●！！！！表の作成はここから！！！！●  -->
				<%
				String message = "";

				if (request.getAttribute("no_item") != null) {
					message = "該当する商品はありません。";
				}

				if (table_items != null) {
					for (Item item : table_items) {
				%>
				<form action="kenshop" method="post">
					<tr class="items">
						<td class="lineup_img"><img src="<%=item.getItemImage()%>"
							alt="<%=item.getItemName()%>" /></td>
						<td class="lineup"><%=item.getItemName()%>&nbsp; &nbsp; <%=item.getItemArtist()%>&nbsp;
							&nbsp; <%=item.getItemPrice()%></td>
						<td class="lineup_btn"><input type="image"
							src="img/btn_addition.gif" alt="カートに追加" /> <input type="hidden"
							name="act" value="add" /> <input type="hidden" name="id"
							value="<%=item.getItemID()%>" /> <input type="hidden"
							name="title" value="<%=item.getItemName()%>" /> <input
							type="hidden" name="create" value="<%=item.getItemArtist()%>" />
							<input type="hidden" name="price"
							value="<%=item.getItemPrice()%>" /></td>
					</tr>
				</form>
				<%
				}
				}
				%>
				<p>
					<font color="red"><%=message%></font>
				</p>
			</table>
			<!-- ↑↑↑↑表の作成はここまで↑↑↑↑  -->
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