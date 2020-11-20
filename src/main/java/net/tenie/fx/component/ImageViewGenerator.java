package net.tenie.fx.component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;

/*   @author tenie */
// 图片加载类
public final class ImageViewGenerator {

	private static Map<String, ImageView> imgMap = new HashMap<String, ImageView>();
	private static Map<String, Region> RegionMap = new HashMap<>();
	private static Map<String, Label> fontImgMap = new HashMap<String, Label>();
	private static final Font GLYPH_FONTAWESOME;
	private static final Map<String, Character> GLYPH_MAP;
	static {
		// 加载字体库文件
		GLYPH_FONTAWESOME = Font
				.loadFont(ImageViewGenerator.class.getResourceAsStream("/image/fontawesome-webfont.ttf"), -1);
		GLYPH_MAP = new HashMap<String, Character>();
	}

	// 修改颜色
	public static void changeColor(Node node, String color) {
		node.setStyle("-fx-background-color: " + color + ";");
	}

	public static Region svgImageUnactive(String name) {
		return svgImage(name, 16, "#A9A9A9");
	}

	public static Region svgImageDefActive(String name) {
		return svgImage(name, 16, "#1C94FF");
	}

	public static Region svgImageDefActive(String name, double size) {
		return svgImage(name, size, "#13B7FE");
	}

	public static Region svgImage(String name, String color) {
		return svgImage(name, 16, color);
	}

	// svg 图片
	public static Region svgImage(String name, double size, String color) {
		Region svgShape = RegionMap.get(name + size + color + ".svg");
		if (svgShape == null) {
			SVGPath p = new SVGPath();
			p.setContent(getSvgStr(name));

			svgShape = new Region();
			svgShape.setShape(p);
			svgShape.setMinSize(size, size);
			svgShape.setPrefSize(size, size);
			svgShape.setMaxSize(size, size);
			svgShape.setStyle("-fx-background-color: " + color + ";");
		}
		return svgShape;
	}

	public static ImageView svgImage2(String name) {
		ImageView iv = imgMap.get(name + ".svg");
		if (iv == null) {
			InputStream imageData = ImageViewGenerator.class.getClassLoader()
					.getResourceAsStream("image/" + name + ".svg");
			Image image = new Image(imageData, 20.0, 20.0, false, false);

			iv = new ImageView(image);
		}
		return iv;
	}

	// 获取 png 图片
	public static ImageView imageName(String name) {
		ImageView rootIcon = imgMap.get(name);
		if (rootIcon == null) {
			rootIcon = new ImageView(new Image(ImageViewGenerator.class.getResourceAsStream(name)));
		}
		return rootIcon;
	}

	// 获取字体图标
	public static Label fontImgName(String name, int sizeFactor, Color color) {
		Label rootIcon = fontImgMap.get(name);
		if (rootIcon == null) {
			Label lbl = new Label();
			Font f = Font.font(GLYPH_FONTAWESOME.getFamily(), sizeFactor);

			lbl.setFont(f);
			lbl.setText(getfont(name));
			if (color != null) {
				lbl.setTextFill(color);
			}
			rootIcon = lbl;
		}
		return rootIcon;
	}

	public static Label fontImgName(String name, int sizeFactor) {
		return fontImgName(name, sizeFactor, Color.DARKGRAY);
	}

	// 修改 Label中字体颜色
	public static void changeColor(Label lb, Color color) {
		lb.setTextFill(color);
	}

	public static String getSvgStr(String name) {
		switch (name) {
		case "NULL":
			return "";
		case "windows-globe":
			return "M22.167 0c12.242 0 22.167 9.925 22.167 22.167s-9.925 22.167-22.167 22.167S0 34.409 0 22.167 9.925 0 22.167 0zm0 3.167c-.961 0-1.906.071-2.828.21-.198.43-.339.901-.339 1.373 0 1.584-1.583-1.583-3.166 1.584-1.584 3.166 0 0 0 3.166 0 1.584-2.292.438-1.584.792 1.584.792 1.584.792 0 2.375 0 0-3.166-1.583-3.166 0S9.5 14.25 7.917 14.25c-1.583 0 0 3.167-1.583 3.167-1.218 0-.563-1.872-1.635-2.737-.407.949-.74 1.938-.99 2.96 1.068.955 1.247 4.732 2.625 6.11 1.583-1.583 1.583 0 3.166 0 1.584 0 1.584 0 2.375 1.584 1.584 0 2.375 1.583 3.959 3.166 1.583 1.584 4.75 1.584 4.75 3.167 0 1.583-1.584 0-1.584 3.167 0 1.583 0 1.583-1.583 1.583-.997 0-1.994 1.883-2.596 3.278a18.942 18.942 0 0 0 7.346 1.472 18.942 18.942 0 0 0 13.574-5.706l-.907-1.815s1.979-3.166.395-4.75c-1.583-1.583-1.583-3.166-1.583-3.166s-3.166 3.166-4.75 1.583c-1.583-1.584-1.583 0-3.166-3.167-1.584-3.166 1.583-4.75 1.583-4.75s0-3.166 1.583-3.166c1.584 0 3.167-3.167 6.334 0 0 0 1.83-1.22 4.418-1.519a19.013 19.013 0 0 0-3.28-5.166 1.902 1.902 0 0 1-1.139.351c-1.583 0 1.584 3.167 0 3.167-1.583 0-1.583-1.584-3.166-1.584-1.584 0-1.584 1.584-3.167 3.167-1.583 1.584 0 0-1.583-1.583-1.584-1.584 3.166 0 1.583-1.584-1.583-1.583 1.584-1.583 1.584-3.166 0-1.242 1.947-1.51 2.788-1.568-.914-.659-1.889-1.238-2.788-1.599 1.583 1.584-1.584 3.167-3.167 3.167-1.488 0-.18-2.794-.017-4.445a19.017 19.017 0 0 0-5.129-.701zm-19 19a18.951 18.951 0 0 0 6.253 14.09L7.917 33.25c-1.583 0-2.375-3.562-2.375-5.145 0-1.271-.51-2.287.107-3.457-2.234-2.243-.899-1.027-.899-2.481 0-.754-.717-1.508-1.47-2.09a19.192 19.192 0 0 0-.113 2.09z";
		case "zero-data-source-24": //database
			return "M9.625 0C4.989 0 0 1.398 0 4.469v13.062C0 20.601 4.989 22 9.625 22s9.625-1.399 9.625-4.469V4.469C19.25 1.398 14.26 0 9.625 0zm8.25 17.531c0 1.708-3.693 3.094-8.25 3.094-4.556 0-8.25-1.386-8.25-3.094v-2.569c1.421 1.465 4.846 2.226 8.25 2.226 3.403 0 6.829-.761 8.25-2.226v2.569zm0-4.125h-.003l.003.021c0 1.697-3.693 3.072-8.25 3.072-4.556 0-8.25-1.375-8.25-3.072l.003-.021h-.003v-2.569c1.421 1.463 4.846 2.225 8.25 2.225 3.403 0 6.829-.762 8.25-2.225v2.569zm0-4.125h-.003l.003.021c0 1.697-3.693 3.072-8.25 3.072-4.556 0-8.25-1.375-8.25-3.072l.003-.021h-.003V6.919c1.802 1.373 5.104 2.019 8.25 2.019s6.448-.646 8.25-2.018v2.361zm-8.25-1.719c-4.556 0-8.25-1.386-8.25-3.094 0-1.709 3.694-3.094 8.25-3.094 4.557 0 8.25 1.384 8.25 3.094 0 1.709-3.693 3.094-8.25 3.094zm6.188 9.626c.381 0 .688.307.688.688s-.307.688-.688.688a.688.688 0 1 1 0-1.376zm0-4.126c.381 0 .688.307.688.688s-.307.688-.688.688-.688-.307-.688-.688.307-.688.688-.688zm0-4.124c.381 0 .688.307.688.688s-.307.688-.688.688a.688.688 0 0 1 0-1.376z";
		case "zero-rerun":
			return "M15.814 6.507l-2.32 2.32c-.119.118-.281.186-.449.186s-.328-.067-.449-.186l-2.318-2.32a.636.636 0 0 1 .898-.897l1.234 1.236V6.84a5.575 5.575 0 0 0-5.57-5.57c-3.071 0-5.57 2.498-5.57 5.57s2.499 5.57 5.57 5.57a5.542 5.542 0 0 0 3.941-1.631.634.634 0 1 1 .896.896 6.789 6.789 0 0 1-4.837 2.004C3.068 13.678 0 10.611 0 6.84S3.068 0 6.84 0a6.847 6.847 0 0 1 6.84 6.84v.006l1.236-1.236a.635.635 0 0 1 .898.897zm-6.07.235l-5-3v6l5-3z";
		case "bolt":
			return "M1333 566q18 20 7 44l-540 1157q-13 25-42 25-4 0-14-2-17-5-25.5-19t-4.5-30l197-808-406 101q-4 1-12 1-18 0-31-11-18-15-13-39l201-825q4-14 16-23t28-9h328q19 0 32 12.5t13 29.5q0 8-5 18l-171 463 396-98q8-2 12-2 19 0 34 15z";
		case "arrows-alt":
			return "M1411 541l-355 355 355 355 144-144q29-31 70-14 39 17 39 59v448q0 26-19 45t-45 19h-448q-42 0-59-40-17-39 14-69l144-144-355-355-355 355 144 144q31 30 14 69-17 40-59 40h-448q-26 0-45-19t-19-45v-448q0-42 40-59 39-17 69 14l144 144 355-355-355-355-144 144q-19 19-45 19-12 0-24-5-40-17-40-59v-448q0-26 19-45t45-19h448q42 0 59 40 17 39-14 69l-144 144 355 355 355-355-144-144q-31-30-14-69 17-40 59-40h448q26 0 45 19t19 45v448q0 42-39 59-13 5-25 5-26 0-45-19z";
		case "info-circle":
			return "M1152 1376v-160q0-14-9-23t-23-9h-96v-512q0-14-9-23t-23-9h-320q-14 0-23 9t-9 23v160q0 14 9 23t23 9h96v320h-96q-14 0-23 9t-9 23v160q0 14 9 23t23 9h448q14 0 23-9t9-23zm-128-896v-160q0-14-9-23t-23-9h-192q-14 0-23 9t-9 23v160q0 14 9 23t23 9h192q14 0 23-9t9-23zm640 416q0 209-103 385.5t-279.5 279.5-385.5 103-385.5-103-279.5-279.5-103-385.5 103-385.5 279.5-279.5 385.5-103 385.5 103 279.5 279.5 103 385.5z";
		case "floppy-o":
			return "M512 1536h768v-384h-768v384zm896 0h128v-896q0-14-10-38.5t-20-34.5l-281-281q-10-10-34-20t-39-10v416q0 40-28 68t-68 28h-576q-40 0-68-28t-28-68v-416h-128v1280h128v-416q0-40 28-68t68-28h832q40 0 68 28t28 68v416zm-384-928v-320q0-13-9.5-22.5t-22.5-9.5h-192q-13 0-22.5 9.5t-9.5 22.5v320q0 13 9.5 22.5t22.5 9.5h192q13 0 22.5-9.5t9.5-22.5zm640 32v928q0 40-28 68t-68 28h-1344q-40 0-68-28t-28-68v-1344q0-40 28-68t68-28h928q40 0 88 20t76 48l280 280q28 28 48 76t20 88z";
		case "window-close":
			return "M1175 1321l146-146q10-10 10-23t-10-23l-233-233 233-233q10-10 10-23t-10-23l-146-146q-10-10-23-10t-23 10l-233 233-233-233q-10-10-23-10t-23 10l-146 146q-10 10-10 23t10 23l233 233-233 233q-10 10-10 23t10 23l146 146q10 10 23 10t23-10l233-233 233 233q10 10 23 10t23-10zm617-1033v1216q0 66-47 113t-113 47h-1472q-66 0-113-47t-47-113v-1216q0-66 47-113t113-47h1472q66 0 113 47t47 113z";
		case "share-square-o":
			return "M1472 989v259q0 119-84.5 203.5t-203.5 84.5h-832q-119 0-203.5-84.5t-84.5-203.5v-832q0-119 84.5-203.5t203.5-84.5h255q13 0 22.5 9.5t9.5 22.5q0 27-26 32-77 26-133 60-10 4-16 4h-112q-66 0-113 47t-47 113v832q0 66 47 113t113 47h832q66 0 113-47t47-113v-214q0-19 18-29 28-13 54-37 16-16 35-8 21 9 21 29zm237-496l-384 384q-18 19-45 19-12 0-25-5-39-17-39-59v-192h-160q-323 0-438 131-119 137-74 473 3 23-20 34-8 2-12 2-16 0-26-13-10-14-21-31t-39.5-68.5-49.5-99.5-38.5-114-17.5-122q0-49 3.5-91t14-90 28-88 47-81.5 68.5-74 94.5-61.5 124.5-48.5 159.5-30.5 196.5-11h160v-192q0-42 39-59 13-5 25-5 26 0 45 19l384 384q19 19 19 45t-19 45z";
		case "folder-open":
			return "M1943 952q0 31-31 66l-336 396q-43 51-120.5 86.5t-143.5 35.5h-1088q-34 0-60.5-13t-26.5-43q0-31 31-66l336-396q43-51 120.5-86.5t143.5-35.5h1088q34 0 60.5 13t26.5 43zm-343-344v160h-832q-94 0-197 47.5t-164 119.5l-337 396-5 6q0-4-.5-12.5t-.5-12.5v-960q0-92 66-158t158-66h320q92 0 158 66t66 158v32h544q92 0 158 66t66 158z";
		case "arrow-up":
			return "M1675 971q0 51-37 90l-75 75q-38 38-91 38-54 0-90-38l-294-293v704q0 52-37.5 84.5t-90.5 32.5h-128q-53 0-90.5-32.5t-37.5-84.5v-704l-294 293q-36 38-90 38t-90-38l-75-75q-38-38-38-90 0-53 38-91l651-651q35-37 90-37 54 0 91 37l651 651q37 39 37 91z";
		case "arrow-down":
			return "M1675 832q0 53-37 90l-651 652q-39 37-91 37-53 0-90-37l-651-652q-38-36-38-90 0-53 38-91l74-75q39-37 91-37 53 0 90 37l294 294v-704q0-52 38-90t90-38h128q52 0 90 38t38 90v704l294-294q37-37 90-37 52 0 91 37l75 75q37 39 37 91z";
		case "files-o":
			return "M1696 384q40 0 68 28t28 68v1216q0 40-28 68t-68 28h-960q-40 0-68-28t-28-68v-288h-544q-40 0-68-28t-28-68v-672q0-40 20-88t48-76l408-408q28-28 76-48t88-20h416q40 0 68 28t28 68v328q68-40 128-40h416zm-544 213l-299 299h299v-299zm-640-384l-299 299h299v-299zm196 647l316-316v-416h-384v416q0 40-28 68t-68 28h-416v640h512v-256q0-40 20-88t48-76zm956 804v-1152h-384v416q0 40-28 68t-68 28h-416v640h896z";
		case "minus-square":
			return "M1408 960v-128q0-26-19-45t-45-19h-896q-26 0-45 19t-19 45v128q0 26 19 45t45 19h896q26 0 45-19t19-45zm256-544v960q0 119-84.5 203.5t-203.5 84.5h-960q-119 0-203.5-84.5t-84.5-203.5v-960q0-119 84.5-203.5t203.5-84.5h960q119 0 203.5 84.5t84.5 203.5z";
		case "clipboard":
			return "M768 1664h896v-640h-416q-40 0-68-28t-28-68v-416h-384v1152zm256-1440v-64q0-13-9.5-22.5t-22.5-9.5h-704q-13 0-22.5 9.5t-9.5 22.5v64q0 13 9.5 22.5t22.5 9.5h704q13 0 22.5-9.5t9.5-22.5zm256 672h299l-299-299v299zm512 128v672q0 40-28 68t-68 28h-960q-40 0-68-28t-28-68v-160h-544q-40 0-68-28t-28-68v-1344q0-40 28-68t68-28h1088q40 0 68 28t28 68v328q21 13 36 28l408 408q28 28 48 76t20 88z";
		case "refresh":
			return "M1639 1056q0 5-1 7-64 268-268 434.5t-478 166.5q-146 0-282.5-55t-243.5-157l-129 129q-19 19-45 19t-45-19-19-45v-448q0-26 19-45t45-19h448q26 0 45 19t19 45-19 45l-137 137q71 66 161 102t187 36q134 0 250-65t186-179q11-17 53-117 8-23 30-23h192q13 0 22.5 9.5t9.5 22.5zm25-800v448q0 26-19 45t-45 19h-448q-26 0-45-19t-19-45 19-45l138-138q-148-137-349-137-134 0-250 65t-186 179q-11 17-53 117-8 23-30 23h-199q-13 0-22.5-9.5t-9.5-22.5v-7q65-268 270-434.5t480-166.5q146 0 284 55.5t245 156.5l130-129q19-19 45-19t45 19 19 45z";
		case "question-circle":
			return "M1024 1376v-192q0-14-9-23t-23-9h-192q-14 0-23 9t-9 23v192q0 14 9 23t23 9h192q14 0 23-9t9-23zm256-672q0-88-55.5-163t-138.5-116-170-41q-243 0-371 213-15 24 8 42l132 100q7 6 19 6 16 0 25-12 53-68 86-92 34-24 86-24 48 0 85.5 26t37.5 59q0 38-20 61t-68 45q-63 28-115.5 86.5t-52.5 125.5v36q0 14 9 23t23 9h192q14 0 23-9t9-23q0-19 21.5-49.5t54.5-49.5q32-18 49-28.5t46-35 44.5-48 28-60.5 12.5-81zm384 192q0 209-103 385.5t-279.5 279.5-385.5 103-385.5-103-279.5-279.5-103-385.5 103-385.5 279.5-279.5 385.5-103 385.5 103 279.5 279.5 103 385.5z";
		case "plus-square-o":
			return "M1344 800v64q0 14-9 23t-23 9h-352v352q0 14-9 23t-23 9h-64q-14 0-23-9t-9-23v-352h-352q-14 0-23-9t-9-23v-64q0-14 9-23t23-9h352v-352q0-14 9-23t23-9h64q14 0 23 9t9 23v352h352q14 0 23 9t9 23zm128 448v-832q0-66-47-113t-113-47h-832q-66 0-113 47t-47 113v832q0 66 47 113t113 47h832q66 0 113-47t47-113zm128-832v832q0 119-84.5 203.5t-203.5 84.5h-832q-119 0-203.5-84.5t-84.5-203.5v-832q0-119 84.5-203.5t203.5-84.5h832q119 0 203.5 84.5t84.5 203.5z";
		case "link":
			return "M1520 1216q0-40-28-68l-208-208q-28-28-68-28-42 0-72 32 3 3 19 18.5t21.5 21.5 15 19 13 25.5 3.5 27.5q0 40-28 68t-68 28q-15 0-27.5-3.5t-25.5-13-19-15-21.5-21.5-18.5-19q-33 31-33 73 0 40 28 68l206 207q27 27 68 27 40 0 68-26l147-146q28-28 28-67zm-703-705q0-40-28-68l-206-207q-28-28-68-28-39 0-68 27l-147 146q-28 28-28 67 0 40 28 68l208 208q27 27 68 27 42 0 72-31-3-3-19-18.5t-21.5-21.5-15-19-13-25.5-3.5-27.5q0-40 28-68t68-28q15 0 27.5 3.5t25.5 13 19 15 21.5 21.5 18.5 19q33-31 33-73zm895 705q0 120-85 203l-147 146q-83 83-203 83-121 0-204-85l-206-207q-83-83-83-203 0-123 88-209l-88-88q-86 88-208 88-120 0-204-84l-208-208q-84-84-84-204t85-203l147-146q83-83 203-83 121 0 204 85l206 207q83 83 83 203 0 123-88 209l88 88q86-88 208-88 120 0 204 84l208 208q84 84 84 204z";
		case "unlink":
			return "M503 1271l-256 256q-11 9-23 9t-23-9q-9-10-9-23t9-23l256-256q10-9 23-9t23 9q9 10 9 23t-9 23zm169 41v320q0 14-9 23t-23 9-23-9-9-23v-320q0-14 9-23t23-9 23 9 9 23zm-224-224q0 14-9 23t-23 9h-320q-14 0-23-9t-9-23 9-23 23-9h320q14 0 23 9t9 23zm1264 128q0 120-85 203l-147 146q-83 83-203 83-121 0-204-85l-334-335q-21-21-42-56l239-18 273 274q27 27 68 27.5t68-26.5l147-146q28-28 28-67 0-40-28-68l-274-275 18-239q35 21 56 42l336 336q84 86 84 204zm-617-724l-239 18-273-274q-28-28-68-28-39 0-68 27l-147 146q-28 28-28 67 0 40 28 68l274 274-18 240q-35-21-56-42l-336-336q-84-86-84-204 0-120 85-203l147-146q83-83 203-83 121 0 204 85l334 335q21 21 42 56zm633 84q0 14-9 23t-23 9h-320q-14 0-23-9t-9-23 9-23 23-9h320q14 0 23 9t9 23zm-544-544v320q0 14-9 23t-23 9-23-9-9-23v-320q0-14 9-23t23-9 23 9 9 23zm407 151l-256 256q-11 9-23 9t-23-9q-9-10-9-23t9-23l256-256q10-9 23-9t23 9q9 10 9 23t-9 23z";
		case "power-off":
			return "M1664 896q0 156-61 298t-164 245-245 164-298 61-298-61-245-164-164-245-61-298q0-182 80.5-343t226.5-270q43-32 95.5-25t83.5 50q32 42 24.5 94.5t-49.5 84.5q-98 74-151.5 181t-53.5 228q0 104 40.5 198.5t109.5 163.5 163.5 109.5 198.5 40.5 198.5-40.5 163.5-109.5 109.5-163.5 40.5-198.5q0-121-53.5-228t-151.5-181q-42-32-49.5-84.5t24.5-94.5q31-43 84-50t95 25q146 109 226.5 270t80.5 343zm-640-768v640q0 52-38 90t-90 38-90-38-38-90v-640q0-52 38-90t90-38 90 38 38 90z";
		case "edit":
			return "M888 1184l116-116-152-152-116 116v56h96v96h56zm440-720q-16-16-33 1l-350 350q-17 17-1 33t33-1l350-350q17-17 1-33zm80 594v190q0 119-84.5 203.5t-203.5 84.5h-832q-119 0-203.5-84.5t-84.5-203.5v-832q0-119 84.5-203.5t203.5-84.5h832q63 0 117 25 15 7 18 23 3 17-9 29l-49 49q-14 14-32 8-23-6-45-6h-832q-66 0-113 47t-47 113v832q0 66 47 113t113 47h832q66 0 113-47t47-113v-126q0-13 9-22l64-64q15-15 35-7t20 29zm-96-738l288 288-672 672h-288v-288zm444 132l-92 92-288-288 92-92q28-28 68-28t68 28l152 152q28 28 28 68t-28 68z";
		case "trash":
			return "M704 1376v-704q0-14-9-23t-23-9h-64q-14 0-23 9t-9 23v704q0 14 9 23t23 9h64q14 0 23-9t9-23zm256 0v-704q0-14-9-23t-23-9h-64q-14 0-23 9t-9 23v704q0 14 9 23t23 9h64q14 0 23-9t9-23zm256 0v-704q0-14-9-23t-23-9h-64q-14 0-23 9t-9 23v704q0 14 9 23t23 9h64q14 0 23-9t9-23zm-544-992h448l-48-117q-7-9-17-11h-317q-10 2-17 11zm928 32v64q0 14-9 23t-23 9h-96v948q0 83-47 143.5t-113 60.5h-832q-66 0-113-58.5t-47-141.5v-952h-96q-14 0-23-9t-9-23v-64q0-14 9-23t23-9h309l70-167q15-37 54-63t79-26h320q40 0 79 26t54 63l70 167h309q14 0 23 9t9 23z";
		case "play":
			return "M1576 927l-1328 738q-23 13-39.5 3t-16.5-36v-1472q0-26 16.5-36t39.5 3l1328 738q23 13 23 31t-23 31z";
		case "stop":
			return "M1664 192v1408q0 26-19 45t-45 19h-1408q-26 0-45-19t-19-45v-1408q0-26 19-45t45-19h1408q26 0 45 19t19 45z";
		case "search":
			return "M1216 832q0-185-131.5-316.5t-316.5-131.5-316.5 131.5-131.5 316.5 131.5 316.5 316.5 131.5 316.5-131.5 131.5-316.5zm512 832q0 52-38 90t-90 38q-54 0-90-38l-343-342q-179 124-399 124-143 0-273.5-55.5t-225-150-150-225-55.5-273.5 55.5-273.5 150-225 225-150 273.5-55.5 273.5 55.5 225 150 150 225 55.5 273.5q0 220-124 399l343 343q37 37 37 90z";
		case "plus-square":
			return "M1408 960v-128q0-26-19-45t-45-19h-320v-320q0-26-19-45t-45-19h-128q-26 0-45 19t-19 45v320h-320q-26 0-45 19t-19 45v128q0 26 19 45t45 19h320v320q0 26 19 45t45 19h128q26 0 45-19t19-45v-320h320q26 0 45-19t19-45zm256-544v960q0 119-84.5 203.5t-203.5 84.5h-960q-119 0-203.5-84.5t-84.5-203.5v-960q0-119 84.5-203.5t203.5-84.5h960q119 0 203.5 84.5t84.5 203.5z";
		case "plus-circle":
			return "M1344 960v-128q0-26-19-45t-45-19h-256v-256q0-26-19-45t-45-19h-128q-26 0-45 19t-19 45v256h-256q-26 0-45 19t-19 45v128q0 26 19 45t45 19h256v256q0 26 19 45t45 19h128q26 0 45-19t19-45v-256h256q26 0 45-19t19-45zm320-64q0 209-103 385.5t-279.5 279.5-385.5 103-385.5-103-279.5-279.5-103-385.5 103-385.5 279.5-279.5 385.5-103 385.5 103 279.5 279.5 103 385.5z";
		case "save":
			return "M512 1536h768v-384h-768v384zm896 0h128v-896q0-14-10-38.5t-20-34.5l-281-281q-10-10-34-20t-39-10v416q0 40-28 68t-68 28h-576q-40 0-68-28t-28-68v-416h-128v1280h128v-416q0-40 28-68t68-28h832q40 0 68 28t28 68v416zm-384-928v-320q0-13-9.5-22.5t-22.5-9.5h-192q-13 0-22.5 9.5t-9.5 22.5v320q0 13 9.5 22.5t22.5 9.5h192q13 0 22.5-9.5t9.5-22.5zm640 32v928q0 40-28 68t-68 28h-1344q-40 0-68-28t-28-68v-1344q0-40 28-68t68-28h928q40 0 88 20t76 48l280 280q28 28 48 76t20 88z";
		case "i-cursor":
			return "M1216 128q-320 0-320 224v416h128v128h-128v544q0 224 320 224h64v128h-64q-272 0-384-146-112 146-384 146h-64v-128h64q320 0 320-224v-544h-128v-128h128v-416q0-224-320-224h-64v-128h64q272 0 384 146 112-146 384-146h64v128h-64z";
		case "tag":
			return "M576 448q0-53-37.5-90.5t-90.5-37.5-90.5 37.5-37.5 90.5 37.5 90.5 90.5 37.5 90.5-37.5 37.5-90.5zm1067 576q0 53-37 90l-491 492q-39 37-91 37-53 0-90-37l-715-716q-38-37-64.5-101t-26.5-117v-416q0-52 38-90t90-38h416q53 0 117 26.5t102 64.5l715 714q37 39 37 91z";
		case "caret-square-o-left":
			return "M1152 576v640q0 26-19 45t-45 19q-20 0-37-12l-448-320q-27-19-27-52t27-52l448-320q17-12 37-12 26 0 45 19t19 45zm256 800v-960q0-13-9.5-22.5t-22.5-9.5h-960q-13 0-22.5 9.5t-9.5 22.5v960q0 13 9.5 22.5t22.5 9.5h960q13 0 22.5-9.5t9.5-22.5zm256-960v960q0 119-84.5 203.5t-203.5 84.5h-960q-119 0-203.5-84.5t-84.5-203.5v-960q0-119 84.5-203.5t203.5-84.5h960q119 0 203.5 84.5t84.5 203.5z";
		case "caret-square-o-down":
			return "M1273 675q18 35-5 66l-320 448q-19 27-52 27t-52-27l-320-448q-23-31-5-66 17-35 57-35h640q40 0 57 35zm135 701v-960q0-13-9.5-22.5t-22.5-9.5h-960q-13 0-22.5 9.5t-9.5 22.5v960q0 13 9.5 22.5t22.5 9.5h960q13 0 22.5-9.5t9.5-22.5zm256-960v960q0 119-84.5 203.5t-203.5 84.5h-960q-119 0-203.5-84.5t-84.5-203.5v-960q0-119 84.5-203.5t203.5-84.5h960q119 0 203.5 84.5t84.5 203.5z";
		case "caret-square-o-up":
			return "M1273 1117q-17 35-57 35h-640q-40 0-57-35-18-35 5-66l320-448q19-27 52-27t52 27l320 448q23 31 5 66zm135 259v-960q0-13-9.5-22.5t-22.5-9.5h-960q-13 0-22.5 9.5t-9.5 22.5v960q0 13 9.5 22.5t22.5 9.5h960q13 0 22.5-9.5t9.5-22.5zm256-960v960q0 119-84.5 203.5t-203.5 84.5h-960q-119 0-203.5-84.5t-84.5-203.5v-960q0-119 84.5-203.5t203.5-84.5h960q119 0 203.5 84.5t84.5 203.5z";
		case "caret-square-o-right":
			return "M1216 896q0 33-27 52l-448 320q-31 23-66 5-35-17-35-57v-640q0-40 35-57 35-18 66 5l448 320q27 19 27 52zm192 480v-960q0-14-9-23t-23-9h-960q-14 0-23 9t-9 23v960q0 14 9 23t23 9h960q14 0 23-9t9-23zm256-960v960q0 119-84.5 203.5t-203.5 84.5h-960q-119 0-203.5-84.5t-84.5-203.5v-960q0-119 84.5-203.5t203.5-84.5h960q119 0 203.5 84.5t84.5 203.5z";
		case "search-plus":
			return "M1088 800v64q0 13-9.5 22.5t-22.5 9.5h-224v224q0 13-9.5 22.5t-22.5 9.5h-64q-13 0-22.5-9.5t-9.5-22.5v-224h-224q-13 0-22.5-9.5t-9.5-22.5v-64q0-13 9.5-22.5t22.5-9.5h224v-224q0-13 9.5-22.5t22.5-9.5h64q13 0 22.5 9.5t9.5 22.5v224h224q13 0 22.5 9.5t9.5 22.5zm128 32q0-185-131.5-316.5t-316.5-131.5-316.5 131.5-131.5 316.5 131.5 316.5 316.5 131.5 316.5-131.5 131.5-316.5zm512 832q0 53-37.5 90.5t-90.5 37.5q-54 0-90-38l-343-342q-179 124-399 124-143 0-273.5-55.5t-225-150-150-225-55.5-273.5 55.5-273.5 150-225 225-150 273.5-55.5 273.5 55.5 225 150 150 225 55.5 273.5q0 220-124 399l343 343q37 37 37 90z";
		case "th-list":
			return "M512 1248v192q0 40-28 68t-68 28h-320q-40 0-68-28t-28-68v-192q0-40 28-68t68-28h320q40 0 68 28t28 68zm0-512v192q0 40-28 68t-68 28h-320q-40 0-68-28t-28-68v-192q0-40 28-68t68-28h320q40 0 68 28t28 68zm1280 512v192q0 40-28 68t-68 28h-960q-40 0-68-28t-28-68v-192q0-40 28-68t68-28h960q40 0 68 28t28 68zm-1280-1024v192q0 40-28 68t-68 28h-320q-40 0-68-28t-28-68v-192q0-40 28-68t68-28h320q40 0 68 28t28 68zm1280 512v192q0 40-28 68t-68 28h-960q-40 0-68-28t-28-68v-192q0-40 28-68t68-28h960q40 0 68 28t28 68zm0-512v192q0 40-28 68t-68 28h-960q-40 0-68-28t-28-68v-192q0-40 28-68t68-28h960q40 0 68 28t28 68z";
		case "window-restore":
			return "M256 1536h768v-512h-768v512zm1024-512h512v-768h-768v256h96q66 0 113 47t47 113v352zm768-864v960q0 66-47 113t-113 47h-608v352q0 66-47 113t-113 47h-960q-66 0-113-47t-47-113v-960q0-66 47-113t113-47h608v-352q0-66 47-113t113-47h960q66 0 113 47t47 113z";
		case "object-group":
			return "M2048 384h-128v1024h128v384h-384v-128h-1280v128h-384v-384h128v-1024h-128v-384h384v128h1280v-128h384v384zm-256-256v128h128v-128h-128zm-1664 0v128h128v-128h-128zm128 1536v-128h-128v128h128zm1408-128v-128h128v-1024h-128v-128h-1280v128h-128v1024h128v128h1280zm256 128v-128h-128v128h128zm-640-1024h384v768h-896v-256h-384v-768h896v256zm-768 384h640v-512h-640v512zm1024 256v-512h-256v384h-384v128h640z";
		case "gears":
			return "M960 896q0-106-75-181t-181-75-181 75-75 181 75 181 181 75 181-75 75-181zm768 512q0-52-38-90t-90-38-90 38-38 90q0 53 37.5 90.5t90.5 37.5 90.5-37.5 37.5-90.5zm0-1024q0-52-38-90t-90-38-90 38-38 90q0 53 37.5 90.5t90.5 37.5 90.5-37.5 37.5-90.5zm-384 421v185q0 10-7 19.5t-16 10.5l-155 24q-11 35-32 76 34 48 90 115 7 11 7 20 0 12-7 19-23 30-82.5 89.5t-78.5 59.5q-11 0-21-7l-115-90q-37 19-77 31-11 108-23 155-7 24-30 24h-186q-11 0-20-7.5t-10-17.5l-23-153q-34-10-75-31l-118 89q-7 7-20 7-11 0-21-8-144-133-144-160 0-9 7-19 10-14 41-53t47-61q-23-44-35-82l-152-24q-10-1-17-9.5t-7-19.5v-185q0-10 7-19.5t16-10.5l155-24q11-35 32-76-34-48-90-115-7-11-7-20 0-12 7-20 22-30 82-89t79-59q11 0 21 7l115 90q34-18 77-32 11-108 23-154 7-24 30-24h186q11 0 20 7.5t10 17.5l23 153q34 10 75 31l118-89q8-7 20-7 11 0 21 8 144 133 144 160 0 8-7 19-12 16-42 54t-45 60q23 48 34 82l152 23q10 2 17 10.5t7 19.5zm640 533v140q0 16-149 31-12 27-30 52 51 113 51 138 0 4-4 7-122 71-124 71-8 0-46-47t-52-68q-20 2-30 2t-30-2q-14 21-52 68t-46 47q-2 0-124-71-4-3-4-7 0-25 51-138-18-25-30-52-149-15-149-31v-140q0-16 149-31 13-29 30-52-51-113-51-138 0-4 4-7 4-2 35-20t59-34 30-16q8 0 46 46.5t52 67.5q20-2 30-2t30 2q51-71 92-112l6-2q4 0 124 70 4 3 4 7 0 25-51 138 17 23 30 52 149 15 149 31zm0-1024v140q0 16-149 31-12 27-30 52 51 113 51 138 0 4-4 7-122 71-124 71-8 0-46-47t-52-68q-20 2-30 2t-30-2q-14 21-52 68t-46 47q-2 0-124-71-4-3-4-7 0-25 51-138-18-25-30-52-149-15-149-31v-140q0-16 149-31 13-29 30-52-51-113-51-138 0-4 4-7 4-2 35-20t59-34 30-16q8 0 46 46.5t52 67.5q20-2 30-2t30 2q51-71 92-112l6-2q4 0 124 70 4 3 4 7 0 25-51 138 17 23 30 52 149 15 149 31z";
		case "puzzle-piece":
			return "M1728 1098q0 81-44.5 135t-123.5 54q-41 0-77.5-17.5t-59-38-56.5-38-71-17.5q-110 0-110 124 0 39 16 115t15 115v5q-22 0-33 1-34 3-97.5 11.5t-115.5 13.5-98 5q-61 0-103-26.5t-42-83.5q0-37 17.5-71t38-56.5 38-59 17.5-77.5q0-79-54-123.5t-135-44.5q-84 0-143 45.5t-59 127.5q0 43 15 83t33.5 64.5 33.5 53 15 50.5q0 45-46 89-37 35-117 35-95 0-245-24-9-2-27.5-4t-27.5-4l-13-2q-1 0-3-1-2 0-2-1v-1024q2 1 17.5 3.5t34 5 21.5 3.5q150 24 245 24 80 0 117-35 46-44 46-89 0-22-15-50.5t-33.5-53-33.5-64.5-15-83q0-82 59-127.5t144-45.5q80 0 134 44.5t54 123.5q0 41-17.5 77.5t-38 59-38 56.5-17.5 71q0 57 42 83.5t103 26.5q64 0 180-15t163-17v2q-1 2-3.5 17.5t-5 34-3.5 21.5q-24 150-24 245 0 80 35 117 44 46 89 46 22 0 50.5-15t53-33.5 64.5-33.5 83-15q82 0 127.5 59t45.5 143z";
		case "database":
			return "M896 768q237 0 443-43t325-127v170q0 69-103 128t-280 93.5-385 34.5-385-34.5-280-93.5-103-128v-170q119 84 325 127t443 43zm0 768q237 0 443-43t325-127v170q0 69-103 128t-280 93.5-385 34.5-385-34.5-280-93.5-103-128v-170q119 84 325 127t443 43zm0-384q237 0 443-43t325-127v170q0 69-103 128t-280 93.5-385 34.5-385-34.5-280-93.5-103-128v-170q119 84 325 127t443 43zm0-1152q208 0 385 34.5t280 93.5 103 128v128q0 69-103 128t-280 93.5-385 34.5-385-34.5-280-93.5-103-128v-128q0-69 103-128t280-93.5 385-34.5z";
		case "gear":
			return "M1152 896q0-106-75-181t-181-75-181 75-75 181 75 181 181 75 181-75 75-181zm512-109v222q0 12-8 23t-20 13l-185 28q-19 54-39 91 35 50 107 138 10 12 10 25t-9 23q-27 37-99 108t-94 71q-12 0-26-9l-138-108q-44 23-91 38-16 136-29 186-7 28-36 28h-222q-14 0-24.5-8.5t-11.5-21.5l-28-184q-49-16-90-37l-141 107q-10 9-25 9-14 0-25-11-126-114-165-168-7-10-7-23 0-12 8-23 15-21 51-66.5t54-70.5q-27-50-41-99l-183-27q-13-2-21-12.5t-8-23.5v-222q0-12 8-23t19-13l186-28q14-46 39-92-40-57-107-138-10-12-10-24 0-10 9-23 26-36 98.5-107.5t94.5-71.5q13 0 26 10l138 107q44-23 91-38 16-136 29-186 7-28 36-28h222q14 0 24.5 8.5t11.5 21.5l28 184q49 16 90 37l142-107q9-9 24-9 13 0 25 10 129 119 165 170 7 8 7 22 0 12-8 23-15 21-51 66.5t-54 70.5q26 50 41 98l183 28q13 2 21 12.5t8 23.5z";
		case "connectdevelop":
			return "M2048 895q0 21-13 36.5t-33 19.5l-205 356q3 9 3 18 0 20-12.5 35.5t-32.5 19.5l-193 337q3 8 3 16 0 23-16.5 40t-40.5 17q-25 0-41-18h-400q-17 20-43 20t-43-20h-399q-17 20-43 20-23 0-40-16.5t-17-40.5q0-8 4-20l-193-335q-20-4-32.5-19.5t-12.5-35.5q0-9 3-18l-206-356q-20-5-32.5-20.5t-12.5-35.5q0-21 13.5-36.5t33.5-19.5l199-344q0-1-.5-3t-.5-3q0-36 34-51l209-363q-4-10-4-18 0-24 17-40.5t40-16.5q26 0 44 21h396q16-21 43-21t43 21h398q18-21 44-21 23 0 40 16.5t17 40.5q0 6-4 18l207 358q23 1 39 17.5t16 38.5q0 13-7 27l187 324q19 4 31.5 19.5t12.5 35.5zm-985 799h389l-342-354h-143l-342 354h360q18-16 39-16t39 16zm-951-812q1 4 1 13 0 10-2 15l208 360 15 6 188-199v-347l-187-194q-13 8-29 10zm874-784h-388l190 200 554-200h-280q-16 16-38 16t-38-16zm703 1212q1-6 5-11l-64-68-17 79h76zm-106 0l22-105-252-266-296 307 63 64h463zm-88 368l16-28 65-310h-427l333 343q8-4 13-5zm-917 16h5l342-354h-373v335l4 6q14 5 22 13zm-26-384h402l64-66-309-321-157 166v221zm-193 0h163v-189l-168 177q4 8 5 12zm-1-825q0 1 .5 2t.5 2q0 16-8 29l171 177v-269zm194-70v311l153 157 297-314-223-236zm4-304l-4 8v264l205-74-191-201q-6 2-10 3zm891-13h-16l-621 224 213 225zm-424 492l-297 315 311 319 296-307zm-335 312l-136-141v284zm350 364l-42 44h85zm336-348l238 251 132-624-3-5-1-1zm344-400q-8-13-8-29v-2l-216-376q-5-1-13-5l-437 463 310 327zm-1196-124v-223l-163 282zm0 946h-163l163 283v-283zm1085 0l-48 227 130-227h-82zm122-70l207-361q-2-10-2-14 0-1 3-16l-171-296-129 612 77 82q5-3 15-7z";
		case "spinner":
			return "M526 1394q0 53-37.5 90.5t-90.5 37.5q-52 0-90-38t-38-90q0-53 37.5-90.5t90.5-37.5 90.5 37.5 37.5 90.5zm498 206q0 53-37.5 90.5t-90.5 37.5-90.5-37.5-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm-704-704q0 53-37.5 90.5t-90.5 37.5-90.5-37.5-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm1202 498q0 52-38 90t-90 38q-53 0-90.5-37.5t-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm-964-996q0 66-47 113t-113 47-113-47-47-113 47-113 113-47 113 47 47 113zm1170 498q0 53-37.5 90.5t-90.5 37.5-90.5-37.5-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm-640-704q0 80-56 136t-136 56-136-56-56-136 56-136 136-56 136 56 56 136zm530 206q0 93-66 158.5t-158 65.5q-93 0-158.5-65.5t-65.5-158.5q0-92 65.5-158t158.5-66q92 0 158 66t66 158z";
		case "table":
			return "M576 1376v-192q0-14-9-23t-23-9h-320q-14 0-23 9t-9 23v192q0 14 9 23t23 9h320q14 0 23-9t9-23zm0-384v-192q0-14-9-23t-23-9h-320q-14 0-23 9t-9 23v192q0 14 9 23t23 9h320q14 0 23-9t9-23zm512 384v-192q0-14-9-23t-23-9h-320q-14 0-23 9t-9 23v192q0 14 9 23t23 9h320q14 0 23-9t9-23zm-512-768v-192q0-14-9-23t-23-9h-320q-14 0-23 9t-9 23v192q0 14 9 23t23 9h320q14 0 23-9t9-23zm512 384v-192q0-14-9-23t-23-9h-320q-14 0-23 9t-9 23v192q0 14 9 23t23 9h320q14 0 23-9t9-23zm512 384v-192q0-14-9-23t-23-9h-320q-14 0-23 9t-9 23v192q0 14 9 23t23 9h320q14 0 23-9t9-23zm-512-768v-192q0-14-9-23t-23-9h-320q-14 0-23 9t-9 23v192q0 14 9 23t23 9h320q14 0 23-9t9-23zm512 384v-192q0-14-9-23t-23-9h-320q-14 0-23 9t-9 23v192q0 14 9 23t23 9h320q14 0 23-9t9-23zm0-384v-192q0-14-9-23t-23-9h-320q-14 0-23 9t-9 23v192q0 14 9 23t23 9h320q14 0 23-9t9-23zm128-320v1088q0 66-47 113t-113 47h-1344q-66 0-113-47t-47-113v-1088q0-66 47-113t113-47h1344q66 0 113 47t47 113z";
//			case "save" :
//				 return 
//			case "save" :
//				 return 

		}
		return "";
	}

	// 获取 font字符
	public static String getfont(String name) {
		Character val = GLYPH_MAP.get(name);
		if (val == null) {
			// TODO
			switch (name) {
			case "glass":
				GLYPH_MAP.put("glass", '\uf000');
				break;
			case "music":
				GLYPH_MAP.put("music", '\uf001');
				break;
			case "search":
				GLYPH_MAP.put("search", '\uf002');
				break;
			case "envelope-o":
				GLYPH_MAP.put("envelope-o", '\uf003');
				break;
			case "heart":
				GLYPH_MAP.put("heart", '\uf004');
				break;
			case "star":
				GLYPH_MAP.put("star", '\uf005');
				break;
			case "star-o":
				GLYPH_MAP.put("star-o", '\uf006');
				break;
			case "user":
				GLYPH_MAP.put("user", '\uf007');
				break;
			case "film":
				GLYPH_MAP.put("film", '\uf008');
				break;
			case "th-large":
				GLYPH_MAP.put("th-large", '\uf009');
				break;
			case "th":
				GLYPH_MAP.put("th", '\uf00a');
				break;
			case "th-list":
				GLYPH_MAP.put("th-list", '\uf00b');
				break;
			case "check":
				GLYPH_MAP.put("check", '\uf00c');
				break;
			case "times":
				GLYPH_MAP.put("times", '\uf00d');
				break; // remove close ,
			case "search-plus":
				GLYPH_MAP.put("search-plus", '\uf00e');
				break;
			case "search-minus":
				GLYPH_MAP.put("search-minus", '\uf010');
				break;
			case "power-off":
				GLYPH_MAP.put("power-off", '\uf011');
				break;
			case "signal":
				GLYPH_MAP.put("signal", '\uf012');
				break;
			case "cog":
				GLYPH_MAP.put("cog", '\uf013');
				break;
			case "gear":
				GLYPH_MAP.put("gear", '\uf013');
				break;
			case "trash-o":
				GLYPH_MAP.put("trash-o", '\uf014');
				break;
			case "home":
				GLYPH_MAP.put("home", '\uf015');
				break;
			case "file-o":
				GLYPH_MAP.put("file-o", '\uf016');
				break;
			case "clock-o":
				GLYPH_MAP.put("clock-o", '\uf017');
				break;
			case "road":
				GLYPH_MAP.put("road", '\uf018');
				break;
			case "download":
				GLYPH_MAP.put("download", '\uf019');
				break;
			case "arrow-circle-o-down":
				GLYPH_MAP.put("arrow-circle-o-down", '\uf01a');
				break;
			case "arrow-circle-o-up":
				GLYPH_MAP.put("arrow-circle-o-up", '\uf01b');
				break;
			case "inbox":
				GLYPH_MAP.put("inbox", '\uf01c');
				break;
			case "play-circle-o":
				GLYPH_MAP.put("play-circle-o", '\uf01d');
				break;
			case "repeat":
				GLYPH_MAP.put("repeat", '\uf01e');
				break; // rotate-right ,
			case "refresh":
				GLYPH_MAP.put("refresh", '\uf021');
				break;
			case "list-alt":
				GLYPH_MAP.put("list-alt", '\uf022');
				break;
			case "lock":
				GLYPH_MAP.put("lock", '\uf023');
				break;
			case "flag":
				GLYPH_MAP.put("flag", '\uf024');
				break;
			case "headphones":
				GLYPH_MAP.put("headphones", '\uf025');
				break;
			case "volume-off":
				GLYPH_MAP.put("volume-off", '\uf026');
				break;
			case "volume-down":
				GLYPH_MAP.put("volume-down", '\uf027');
				break;
			case "volume-up":
				GLYPH_MAP.put("volume-up", '\uf028');
				break;
			case "qrcode":
				GLYPH_MAP.put("qrcode", '\uf029');
				break;
			case "barcode":
				GLYPH_MAP.put("barcode", '\uf02a');
				break;
			case "tag":
				GLYPH_MAP.put("tag", '\uf02b');
				break;
			case "tags":
				GLYPH_MAP.put("tags", '\uf02c');
				break;
			case "book":
				GLYPH_MAP.put("book", '\uf02d');
				break;
			case "bookmark":
				GLYPH_MAP.put("bookmark", '\uf02e');
				break;
			case "print":
				GLYPH_MAP.put("print", '\uf02f');
				break;
			case "camera":
				GLYPH_MAP.put("camera", '\uf030');
				break;
			case "font":
				GLYPH_MAP.put("font", '\uf031');
				break;
			case "bold":
				GLYPH_MAP.put("bold", '\uf032');
				break;
			case "italic":
				GLYPH_MAP.put("italic", '\uf033');
				break;
			case "text-height":
				GLYPH_MAP.put("text-height", '\uf034');
				break;
			case "text-width":
				GLYPH_MAP.put("text-width", '\uf035');
				break;
			case "align-left":
				GLYPH_MAP.put("align-left", '\uf036');
				break;
			case "align-center":
				GLYPH_MAP.put("align-center", '\uf037');
				break;
			case "align-right":
				GLYPH_MAP.put("align-right", '\uf038');
				break;
			case "align-justify":
				GLYPH_MAP.put("align-justify", '\uf039');
				break;
			case "list":
				GLYPH_MAP.put("list", '\uf03a');
				break;
			case "outdent":
				GLYPH_MAP.put("outdent", '\uf03b');
				break; // dedent ,
			case "indent":
				GLYPH_MAP.put("indent", '\uf03c');
				break;
			case "video-camera":
				GLYPH_MAP.put("video-camera", '\uf03d');
				break;
			case "picture-o":
				GLYPH_MAP.put("picture-o", '\uf03e');
				break; // photo! image!
			case "pencil":
				GLYPH_MAP.put("pencil", '\uf040');
				break;
			case "map-marker":
				GLYPH_MAP.put("map-marker", '\uf041');
				break;
			case "adjust":
				GLYPH_MAP.put("adjust", '\uf042');
				break;
			case "tint":
				GLYPH_MAP.put("tint", '\uf043');
				break;
			case "pencil-square-o":
				GLYPH_MAP.put("pencil-square-o", '\uf044');
				break; // edit ,
			case "edit":
				GLYPH_MAP.put("edit", '\uf044');
				break; // ,
			case "share-square-o":
				GLYPH_MAP.put("share-square-o", '\uf045');
				break;
			case "check-square-o":
				GLYPH_MAP.put("check-square-o", '\uf046');
				break;
			case "arrows":
				GLYPH_MAP.put("arrows", '\uf047');
				break;
			case "step-backward":
				GLYPH_MAP.put("step-backward", '\uf048');
				break;
			case "fast-backward":
				GLYPH_MAP.put("fast-backward", '\uf049');
				break;
			case "backward":
				GLYPH_MAP.put("backward", '\uf04a');
				break;
			case "play":
				GLYPH_MAP.put("play", '\uf04b');
				break;
			case "pause":
				GLYPH_MAP.put("pause", '\uf04c');
				break;
			case "stop":
				GLYPH_MAP.put("stop", '\uf04d');
				break;
			case "forward":
				GLYPH_MAP.put("forward", '\uf04e');
				break;
			case "fast-forward":
				GLYPH_MAP.put("fast-forward", '\uf050');
				break;
			case "step-forward":
				GLYPH_MAP.put("step-forward", '\uf051');
				break;
			case "eject":
				GLYPH_MAP.put("eject", '\uf052');
				break;
			case "chevron-left":
				GLYPH_MAP.put("chevron-left", '\uf053');
				break;
			case "chevron-right":
				GLYPH_MAP.put("chevron-right", '\uf054');
				break;
			case "plus-circle":
				GLYPH_MAP.put("plus-circle", '\uf055');
				break;
			case "minus-circle":
				GLYPH_MAP.put("minus-circle", '\uf056');
				break;
			case "times-circle":
				GLYPH_MAP.put("times-circle", '\uf057');
				break;
			case "check-circle":
				GLYPH_MAP.put("check-circle", '\uf058');
				break;
			case "question-circle":
				GLYPH_MAP.put("question-circle", '\uf059');
				break;
			case "info-circle":
				GLYPH_MAP.put("info-circle", '\uf05a');
				break;
			case "crosshairs":
				GLYPH_MAP.put("crosshairs", '\uf05b');
				break;
			case "times-circle-o":
				GLYPH_MAP.put("times-circle-o", '\uf05c');
				break;
			case "check-circle-o":
				GLYPH_MAP.put("check-circle-o", '\uf05d');
				break;
			case "ban":
				GLYPH_MAP.put("ban", '\uf05e');
				break;
			case "arrow-left":
				GLYPH_MAP.put("arrow-left", '\uf060');
				break;
			case "arrow-right":
				GLYPH_MAP.put("arrow-right", '\uf061');
				break;
			case "arrow-up":
				GLYPH_MAP.put("arrow-up", '\uf062');
				break;
			case "arrow-down":
				GLYPH_MAP.put("arrow-down", '\uf063');
				break;
			case "share":
				GLYPH_MAP.put("share", '\uf064');
				break; // mail-forward ,
			case "expand":
				GLYPH_MAP.put("expand", '\uf065');
				break;
			case "compress":
				GLYPH_MAP.put("compress", '\uf066');
				break;
			case "plus":
				GLYPH_MAP.put("plus", '\uf067');
				break;
			case "minus":
				GLYPH_MAP.put("minus", '\uf068');
				break;
			case "asterisk":
				GLYPH_MAP.put("asterisk", '\uf069');
				break;
			case "exclamation-circle":
				GLYPH_MAP.put("exclamation-circle", '\uf06a');
				break;
			case "gift":
				GLYPH_MAP.put("gift", '\uf06b');
				break;
			case "leaf":
				GLYPH_MAP.put("leaf", '\uf06c');
				break;
			case "fire":
				GLYPH_MAP.put("fire", '\uf06d');
				break;
			case "eye":
				GLYPH_MAP.put("eye", '\uf06e');
				break;
			case "eye-slash":
				GLYPH_MAP.put("eye-slash", '\uf070');
				break;
			case "exclamation-triangle":
				GLYPH_MAP.put("exclamation-triangle", '\uf071');
				break; // warning ,
			case "plane":
				GLYPH_MAP.put("plane", '\uf072');
				break;
			case "calendar":
				GLYPH_MAP.put("calendar", '\uf073');
				break;
			case "random":
				GLYPH_MAP.put("random", '\uf074');
				break;
			case "comment":
				GLYPH_MAP.put("comment", '\uf075');
				break;
			case "magnet":
				GLYPH_MAP.put("magnet", '\uf076');
				break;
			case "chevron-up":
				GLYPH_MAP.put("chevron-up", '\uf077');
				break;
			case "chevron-down":
				GLYPH_MAP.put("chevron-down", '\uf078');
				break;
			case "retweet":
				GLYPH_MAP.put("retweet", '\uf079');
				break;
			case "shopping-cart":
				GLYPH_MAP.put("shopping-cart", '\uf07a');
				break;
			case "folder":
				GLYPH_MAP.put("folder", '\uf07b');
				break;
			case "folder-open":
				GLYPH_MAP.put("folder-open", '\uf07c');
				break;
			case "arrows-v":
				GLYPH_MAP.put("arrows-v", '\uf07d');
				break;
			case "arrows-h":
				GLYPH_MAP.put("arrows-h", '\uf07e');
				break;
			case "bar-chart":
				GLYPH_MAP.put("bar-chart", '\uf080');
				break; // bar-chart-o ,
			case "twitter-square":
				GLYPH_MAP.put("twitter-square", '\uf081');
				break;
			case "facebook-square":
				GLYPH_MAP.put("facebook-square", '\uf082');
				break;
			case "camera-retro":
				GLYPH_MAP.put("camera-retro", '\uf083');
				break;
			case "key":
				GLYPH_MAP.put("key", '\uf084');
				break;
			case "cogs":
				GLYPH_MAP.put("cogs", '\uf085');
				break; // gears ,
			case "gears":
				GLYPH_MAP.put("gears", '\uf085');
				break; // gears ,
			case "comments":
				GLYPH_MAP.put("comments", '\uf086');
				break;
			case "thumbs-o-up":
				GLYPH_MAP.put("thumbs-o-up", '\uf087');
				break;
			case "thumbs-o-down":
				GLYPH_MAP.put("thumbs-o-down", '\uf088');
				break;
			case "star-half":
				GLYPH_MAP.put("star-half", '\uf089');
				break;
			case "heart-o":
				GLYPH_MAP.put("heart-o", '\uf08a');
				break;
			case "sign-out":
				GLYPH_MAP.put("sign-out", '\uf08b');
				break;
			case "linkedin-square":
				GLYPH_MAP.put("linkedin-square", '\uf08c');
				break;
			case "thumb-tack":
				GLYPH_MAP.put("thumb-tack", '\uf08d');
				break;
			case "external-link":
				GLYPH_MAP.put("external-link", '\uf08e');
				break;
			case "sign-in":
				GLYPH_MAP.put("sign-in", '\uf090');
				break;
			case "trophy":
				GLYPH_MAP.put("trophy", '\uf091');
				break;
			case "github-square":
				GLYPH_MAP.put("github-square", '\uf092');
				break;
			case "upload":
				GLYPH_MAP.put("upload", '\uf093');
				break;
			case "lemon-o":
				GLYPH_MAP.put("lemon-o", '\uf094');
				break;
			case "phone":
				GLYPH_MAP.put("phone", '\uf095');
				break;
			case "square-o":
				GLYPH_MAP.put("square-o", '\uf096');
				break;
			case "bookmark-o":
				GLYPH_MAP.put("bookmark-o", '\uf097');
				break;
			case "phone-square":
				GLYPH_MAP.put("phone-square", '\uf098');
				break;
			case "twitter":
				GLYPH_MAP.put("twitter", '\uf099');
				break;
			case "facebook":
				GLYPH_MAP.put("facebook", '\uf09a');
				break; // facebook-f ,
			case "github":
				GLYPH_MAP.put("github", '\uf09b');
				break;
			case "unlock":
				GLYPH_MAP.put("unlock", '\uf09c');
				break;
			case "credit-card":
				GLYPH_MAP.put("credit-card", '\uf09d');
				break;
			case "rss":
				GLYPH_MAP.put("rss", '\uf09e');
				break; // feed ,
			case "hdd-o":
				GLYPH_MAP.put("hdd-o", '\uf0a0');
				break;
			case "bullhorn":
				GLYPH_MAP.put("bullhorn", '\uf0a1');
				break;
			case "bell":
				GLYPH_MAP.put("bell", '\uf0f3');
				break;
			case "certificate":
				GLYPH_MAP.put("certificate", '\uf0a3');
				break;
			case "hand-o-right":
				GLYPH_MAP.put("hand-o-right", '\uf0a4');
				break;
			case "hand-o-left":
				GLYPH_MAP.put("hand-o-left", '\uf0a5');
				break;
			case "hand-o-up":
				GLYPH_MAP.put("hand-o-up", '\uf0a6');
				break;
			case "hand-o-down":
				GLYPH_MAP.put("hand-o-down", '\uf0a7');
				break;
			case "arrow-circle-left":
				GLYPH_MAP.put("arrow-circle-left", '\uf0a8');
				break;
			case "arrow-circle-right":
				GLYPH_MAP.put("arrow-circle-right", '\uf0a9');
				break;
			case "arrow-circle-up":
				GLYPH_MAP.put("arrow-circle-up", '\uf0aa');
				break;
			case "arrow-circle-down":
				GLYPH_MAP.put("arrow-circle-down", '\uf0ab');
				break;
			case "globe":
				GLYPH_MAP.put("globe", '\uf0ac');
				break;
			case "wrench":
				GLYPH_MAP.put("wrench", '\uf0ad');
				break;
			case "tasks":
				GLYPH_MAP.put("tasks", '\uf0ae');
				break;
			case "filter":
				GLYPH_MAP.put("filter", '\uf0b0');
				break;
			case "briefcase":
				GLYPH_MAP.put("briefcase", '\uf0b1');
				break;
			case "arrows-alt":
				GLYPH_MAP.put("arrows-alt", '\uf0b2');
				break;
			case "users":
				GLYPH_MAP.put("users", '\uf0c0');
				break; // group ,
			case "link":
				GLYPH_MAP.put("link", '\uf0c1');
				break; // chain ,
			case "cloud":
				GLYPH_MAP.put("cloud", '\uf0c2');
				break;
			case "flask":
				GLYPH_MAP.put("flask", '\uf0c3');
				break;
			case "scissors":
				GLYPH_MAP.put("scissors", '\uf0c4');
				break; // cut ,
			case "files-o":
				GLYPH_MAP.put("files-o", '\uf0c5');
				break; // copy ,
			case "paperclip":
				GLYPH_MAP.put("paperclip", '\uf0c6');
				break;
			case "floppy-o":
				GLYPH_MAP.put("floppy-o", '\uf0c7');
				break; // ,
			case "save":
				GLYPH_MAP.put("save", '\uf0c7');
				break;
			case "square":
				GLYPH_MAP.put("square", '\uf0c8');
				break;
			case "bars":
				GLYPH_MAP.put("bars", '\uf0c9');
				break; // navicon! reorder ,
			case "list-ul":
				GLYPH_MAP.put("list-ul", '\uf0ca');
				break;
			case "list-ol":
				GLYPH_MAP.put("list-ol", '\uf0cb');
				break;
			case "strikethrough":
				GLYPH_MAP.put("strikethrough", '\uf0cc');
				break;
			case "underline":
				GLYPH_MAP.put("underline", '\uf0cd');
				break;
			case "table":
				GLYPH_MAP.put("table", '\uf0ce');
				break;
			case "magic":
				GLYPH_MAP.put("magic", '\uf0d0');
				break;
			case "truck":
				GLYPH_MAP.put("truck", '\uf0d1');
				break;
			case "pinterest":
				GLYPH_MAP.put("pinterest", '\uf0d2');
				break;
			case "pinterest-square":
				GLYPH_MAP.put("pinterest-square", '\uf0d3');
				break;
			case "google-plus-square":
				GLYPH_MAP.put("google-plus-square", '\uf0d4');
				break;
			case "google-plus":
				GLYPH_MAP.put("google-plus", '\uf0d5');
				break;
			case "money":
				GLYPH_MAP.put("money", '\uf0d6');
				break;
			case "caret-down":
				GLYPH_MAP.put("caret-down", '\uf0d7');
				break;
			case "caret-up":
				GLYPH_MAP.put("caret-up", '\uf0d8');
				break;
			case "caret-left":
				GLYPH_MAP.put("caret-left", '\uf0d9');
				break;
			case "caret-right":
				GLYPH_MAP.put("caret-right", '\uf0da');
				break;
			case "columns":
				GLYPH_MAP.put("columns", '\uf0db');
				break;
			case "sort":
				GLYPH_MAP.put("sort", '\uf0dc');
				break; // unsorted ,
			case "sort-desc":
				GLYPH_MAP.put("sort-desc", '\uf0dd');
				break; // sort-down ,
			case "sort-asc":
				GLYPH_MAP.put("sort-asc", '\uf0de');
				break; // sort-up ,
			case "envelope":
				GLYPH_MAP.put("envelope", '\uf0e0');
				break;
			case "linkedin":
				GLYPH_MAP.put("linkedin", '\uf0e1');
				break;
			case "undo":
				GLYPH_MAP.put("undo", '\uf0e2');
				break; // rotate-left ,
			case "gavel":
				GLYPH_MAP.put("gavel", '\uf0e3');
				break; // legal ,
			case "tachometer":
				GLYPH_MAP.put("tachometer", '\uf0e4');
				break; // dashboard ,
			case "comment-o":
				GLYPH_MAP.put("comment-o", '\uf0e5');
				break;
			case "comments-o":
				GLYPH_MAP.put("comments-o", '\uf0e6');
				break;
			case "bolt":
				GLYPH_MAP.put("bolt", '\uf0e7');
				break; // flash ,
			case "sitemap":
				GLYPH_MAP.put("sitemap", '\uf0e8');
				break;
			case "umbrella":
				GLYPH_MAP.put("umbrella", '\uf0e9');
				break;
			case "clipboard":
				GLYPH_MAP.put("clipboard", '\uf0ea');
				break;// paste ,
			case "lightbulb-o":
				GLYPH_MAP.put("lightbulb-o", '\uf0eb');
				break;
			case "exchange":
				GLYPH_MAP.put("exchange", '\uf0ec');
				break;
			case "cloud-download":
				GLYPH_MAP.put("cloud-download", '\uf0ed');
				break;
			case "cloud-upload":
				GLYPH_MAP.put("cloud-upload", '\uf0ee');
				break;
			case "user-md":
				GLYPH_MAP.put("user-md", '\uf0f0');
				break;
			case "stethoscope":
				GLYPH_MAP.put("stethoscope", '\uf0f1');
				break;
			case "suitcase":
				GLYPH_MAP.put("suitcase", '\uf0f2');
				break;
			case "bell-o":
				GLYPH_MAP.put("bell-o", '\uf0a2');
				break;
			case "coffee":
				GLYPH_MAP.put("coffee", '\uf0f4');
				break;
			case "cutlery":
				GLYPH_MAP.put("cutlery", '\uf0f5');
				break;
			case "file-text-o":
				GLYPH_MAP.put("file-text-o", '\uf0f6');
				break;
			case "building-o":
				GLYPH_MAP.put("building-o", '\uf0f7');
				break;
			case "hospital-o":
				GLYPH_MAP.put("hospital-o", '\uf0f8');
				break;
			case "ambulance":
				GLYPH_MAP.put("ambulance", '\uf0f9');
				break;
			case "medkit":
				GLYPH_MAP.put("medkit", '\uf0fa');
				break;
			case "fighter-jet":
				GLYPH_MAP.put("fighter-jet", '\uf0fb');
				break;
			case "beer":
				GLYPH_MAP.put("beer", '\uf0fc');
				break;
			case "h-square":
				GLYPH_MAP.put("h-square", '\uf0fd');
				break;
			case "plus-square":
				GLYPH_MAP.put("plus-square", '\uf0fe');
				break;
			case "angle-double-left":
				GLYPH_MAP.put("angle-double-left", '\uf100');
				break;
			case "angle-double-right":
				GLYPH_MAP.put("angle-double-right", '\uf101');
				break;
			case "angle-double-up":
				GLYPH_MAP.put("angle-double-up", '\uf102');
				break;
			case "angle-double-down":
				GLYPH_MAP.put("angle-double-down", '\uf103');
				break;
			case "angle-left":
				GLYPH_MAP.put("angle-left", '\uf104');
				break;
			case "angle-right":
				GLYPH_MAP.put("angle-right", '\uf105');
				break;
			case "angle-up":
				GLYPH_MAP.put("angle-up", '\uf106');
				break;
			case "angle-down":
				GLYPH_MAP.put("angle-down", '\uf107');
				break;
			case "desktop":
				GLYPH_MAP.put("desktop", '\uf108');
				break;
			case "laptop":
				GLYPH_MAP.put("laptop", '\uf109');
				break;
			case "tablet":
				GLYPH_MAP.put("tablet", '\uf10a');
				break;
			case "mobile":
				GLYPH_MAP.put("mobile", '\uf10b');
				break; // mobile-phone ,
			case "circle-o":
				GLYPH_MAP.put("circle-o", '\uf10c');
				break;
			case "quote-left":
				GLYPH_MAP.put("quote-left", '\uf10d');
				break;
			case "quote-right":
				GLYPH_MAP.put("quote-right", '\uf10e');
				break;
			case "spinner":
				GLYPH_MAP.put("spinner", '\uf110');
				break;
			case "circle":
				GLYPH_MAP.put("circle", '\uf111');
				break;
			case "reply":
				GLYPH_MAP.put("reply", '\uf112');
				break; // mail-reply ,
			case "github-alt":
				GLYPH_MAP.put("github-alt", '\uf113');
				break;
			case "folder-o":
				GLYPH_MAP.put("folder-o", '\uf114');
				break;
			case "folder-open-o":
				GLYPH_MAP.put("folder-open-o", '\uf115');
				break;
			case "smile-o":
				GLYPH_MAP.put("smile-o", '\uf118');
				break;
			case "frown-o":
				GLYPH_MAP.put("frown-o", '\uf119');
				break;
			case "meh-o":
				GLYPH_MAP.put("meh-o", '\uf11a');
				break;
			case "gamepad":
				GLYPH_MAP.put("gamepad", '\uf11b');
				break;
			case "keyboard-o":
				GLYPH_MAP.put("keyboard-o", '\uf11c');
				break;
			case "flag-o":
				GLYPH_MAP.put("flag-o", '\uf11d');
				break;
			case "flag-checkered":
				GLYPH_MAP.put("flag-checkered", '\uf11e');
				break;
			case "terminal":
				GLYPH_MAP.put("terminal", '\uf120');
				break;
			case "code":
				GLYPH_MAP.put("code", '\uf121');
				break;
			case "reply-all":
				GLYPH_MAP.put("reply-all", '\uf122');
				break; // mail-reply-all ,
			case "star-half-o":
				GLYPH_MAP.put("star-half-o", '\uf123');
				break; // star-half-empty! star-half-full ,
			case "location-arrow":
				GLYPH_MAP.put("location-arrow", '\uf124');
				break;
			case "crop":
				GLYPH_MAP.put("crop", '\uf125');
				break;
			case "code-fork":
				GLYPH_MAP.put("code-fork", '\uf126');
				break;
			case "chain-broken":
				GLYPH_MAP.put("chain-broken", '\uf127');
				break; // unlink ,
			case "unlink":
				GLYPH_MAP.put("unlink", '\uf127');
				break;
			case "question":
				GLYPH_MAP.put("question", '\uf128');
				break;
			case "info":
				GLYPH_MAP.put("info", '\uf129');
				break;
			case "exclamation":
				GLYPH_MAP.put("exclamation", '\uf12a');
				break;
			case "superscript":
				GLYPH_MAP.put("superscript", '\uf12b');
				break;
			case "subscript":
				GLYPH_MAP.put("subscript", '\uf12c');
				break;
			case "eraser":
				GLYPH_MAP.put("eraser", '\uf12d');
				break;
			case "puzzle-piece":
				GLYPH_MAP.put("puzzle-piece", '\uf12e');
				break;
			case "microphone":
				GLYPH_MAP.put("microphone", '\uf130');
				break;
			case "microphone-slash":
				GLYPH_MAP.put("microphone-slash", '\uf131');
				break;
			case "shield":
				GLYPH_MAP.put("shield", '\uf132');
				break;
			case "calendar-o":
				GLYPH_MAP.put("calendar-o", '\uf133');
				break;
			case "fire-extinguisher":
				GLYPH_MAP.put("fire-extinguisher", '\uf134');
				break;
			case "rocket":
				GLYPH_MAP.put("rocket", '\uf135');
				break;
			case "maxcdn":
				GLYPH_MAP.put("maxcdn", '\uf136');
				break;
			case "chevron-circle-left":
				GLYPH_MAP.put("chevron-circle-left", '\uf137');
				break;
			case "chevron-circle-right":
				GLYPH_MAP.put("chevron-circle-right", '\uf138');
				break;
			case "chevron-circle-up":
				GLYPH_MAP.put("chevron-circle-up", '\uf139');
				break;
			case "chevron-circle-down":
				GLYPH_MAP.put("chevron-circle-down", '\uf13a');
				break;
			case "html5":
				GLYPH_MAP.put("html5", '\uf13b');
				break;
			case "css3":
				GLYPH_MAP.put("css3", '\uf13c');
				break;
			case "anchor":
				GLYPH_MAP.put("anchor", '\uf13d');
				break;
			case "unlock-alt":
				GLYPH_MAP.put("unlock-alt", '\uf13e');
				break;
			case "bullseye":
				GLYPH_MAP.put("bullseye", '\uf140');
				break;
			case "ellipsis-h":
				GLYPH_MAP.put("ellipsis-h", '\uf141');
				break;
			case "ellipsis-v":
				GLYPH_MAP.put("ellipsis-v", '\uf142');
				break;
			case "rss-square":
				GLYPH_MAP.put("rss-square", '\uf143');
				break;
			case "play-circle":
				GLYPH_MAP.put("play-circle", '\uf144');
				break;
			case "ticket":
				GLYPH_MAP.put("ticket", '\uf145');
				break;
			case "minus-square":
				GLYPH_MAP.put("minus-square", '\uf146');
				break;
			case "minus-square-o":
				GLYPH_MAP.put("minus-square-o", '\uf147');
				break;
			case "level-up":
				GLYPH_MAP.put("level-up", '\uf148');
				break;
			case "level-down":
				GLYPH_MAP.put("level-down", '\uf149');
				break;
			case "check-square":
				GLYPH_MAP.put("check-square", '\uf14a');
				break;
			case "pencil-square":
				GLYPH_MAP.put("pencil-square", '\uf14b');
				break;
			case "external-link-square":
				GLYPH_MAP.put("external-link-square", '\uf14c');
				break;
			case "share-square":
				GLYPH_MAP.put("share-square", '\uf14d');
				break;
			case "compass":
				GLYPH_MAP.put("compass", '\uf14e');
				break;
			case "caret-square-o-down":
				GLYPH_MAP.put("caret-square-o-down", '\uf150');
				break; // toggle-down ,
			case "caret-square-o-up":
				GLYPH_MAP.put("caret-square-o-up", '\uf151');
				break; // toggle-up ,
			case "caret-square-o-right":
				GLYPH_MAP.put("caret-square-o-right", '\uf152');
				break; // toggle-right ,
			case "eur":
				GLYPH_MAP.put("eur", '\uf153');
				break; // euro ,
			case "gbp":
				GLYPH_MAP.put("gbp", '\uf154');
				break;
			case "usd":
				GLYPH_MAP.put("usd", '\uf155');
				break; // dollar ,
			case "inr":
				GLYPH_MAP.put("inr", '\uf156');
				break; // rupee ,
			case "jpy":
				GLYPH_MAP.put("jpy", '\uf157');
				break; // cny! rmb! yen!
			case "rub":
				GLYPH_MAP.put("rub", '\uf158');
				break; // ruble! rouble ,
			case "krw":
				GLYPH_MAP.put("krw", '\uf159');
				break; // won ,
			case "btc":
				GLYPH_MAP.put("btc", '\uf15a');
				break; // bitcoin ,
			case "file":
				GLYPH_MAP.put("file", '\uf15b');
				break;
			case "file-text":
				GLYPH_MAP.put("file-text", '\uf15c');
				break;
			case "sort-alpha-asc":
				GLYPH_MAP.put("sort-alpha-asc", '\uf15d');
				break;
			case "sort-alpha-desc":
				GLYPH_MAP.put("sort-alpha-desc", '\uf15e');
				break;
			case "sort-amount-asc":
				GLYPH_MAP.put("sort-amount-asc", '\uf160');
				break;
			case "sort-amount-desc":
				GLYPH_MAP.put("sort-amount-desc", '\uf161');
				break;
			case "sort-numeric-asc":
				GLYPH_MAP.put("sort-numeric-asc", '\uf162');
				break;
			case "sort-numeric-desc":
				GLYPH_MAP.put("sort-numeric-desc", '\uf163');
				break;
			case "thumbs-up":
				GLYPH_MAP.put("thumbs-up", '\uf164');
				break;
			case "thumbs-down":
				GLYPH_MAP.put("thumbs-down", '\uf165');
				break;
			case "youtube-square":
				GLYPH_MAP.put("youtube-square", '\uf166');
				break;
			case "youtube":
				GLYPH_MAP.put("youtube", '\uf167');
				break;
			case "xing":
				GLYPH_MAP.put("xing", '\uf168');
				break;
			case "xing-square":
				GLYPH_MAP.put("xing-square", '\uf169');
				break;
			case "youtube-play":
				GLYPH_MAP.put("youtube-play", '\uf16a');
				break;
			case "dropbox":
				GLYPH_MAP.put("dropbox", '\uf16b');
				break;
			case "stack-overflow":
				GLYPH_MAP.put("stack-overflow", '\uf16c');
				break;
			case "instagram":
				GLYPH_MAP.put("instagram", '\uf16d');
				break;
			case "flickr":
				GLYPH_MAP.put("flickr", '\uf16e');
				break;
			case "adn":
				GLYPH_MAP.put("adn", '\uf170');
				break;
			case "bitbucket":
				GLYPH_MAP.put("bitbucket", '\uf171');
				break;
			case "bitbucket-square":
				GLYPH_MAP.put("bitbucket-square", '\uf172');
				break;
			case "tumblr":
				GLYPH_MAP.put("tumblr", '\uf173');
				break;
			case "tumblr-square":
				GLYPH_MAP.put("tumblr-square", '\uf174');
				break;
			case "long-arrow-down":
				GLYPH_MAP.put("long-arrow-down", '\uf175');
				break;
			case "long-arrow-up":
				GLYPH_MAP.put("long-arrow-up", '\uf176');
				break;
			case "long-arrow-left":
				GLYPH_MAP.put("long-arrow-left", '\uf177');
				break;
			case "long-arrow-right":
				GLYPH_MAP.put("long-arrow-right", '\uf178');
				break;
			case "apple":
				GLYPH_MAP.put("apple", '\uf179');
				break;
			case "windows":
				GLYPH_MAP.put("windows", '\uf17a');
				break;
			case "android":
				GLYPH_MAP.put("android", '\uf17b');
				break;
			case "linux":
				GLYPH_MAP.put("linux", '\uf17c');
				break;
			case "dribbble":
				GLYPH_MAP.put("dribbble", '\uf17d');
				break;
			case "skype":
				GLYPH_MAP.put("skype", '\uf17e');
				break;
			case "foursquare":
				GLYPH_MAP.put("foursquare", '\uf180');
				break;
			case "trello":
				GLYPH_MAP.put("trello", '\uf181');
				break;
			case "female":
				GLYPH_MAP.put("female", '\uf182');
				break;
			case "male":
				GLYPH_MAP.put("male", '\uf183');
				break;
			case "gratipay":
				GLYPH_MAP.put("gratipay", '\uf184');
				break; // gittip ,
			case "sun-o":
				GLYPH_MAP.put("sun-o", '\uf185');
				break;
			case "moon-o":
				GLYPH_MAP.put("moon-o", '\uf186');
				break;
			case "archive":
				GLYPH_MAP.put("archive", '\uf187');
				break;
			case "bug":
				GLYPH_MAP.put("bug", '\uf188');
				break;
			case "vk":
				GLYPH_MAP.put("vk", '\uf189');
				break;
			case "weibo":
				GLYPH_MAP.put("weibo", '\uf18a');
				break;
			case "renren":
				GLYPH_MAP.put("renren", '\uf18b');
				break;
			case "pagelines":
				GLYPH_MAP.put("pagelines", '\uf18c');
				break;
			case "stack-exchange":
				GLYPH_MAP.put("stack-exchange", '\uf18d');
				break;
			case "arrow-circle-o-right":
				GLYPH_MAP.put("arrow-circle-o-right", '\uf18e');
				break;
			case "arrow-circle-o-left":
				GLYPH_MAP.put("arrow-circle-o-left", '\uf190');
				break;
			case "caret-square-o-left":
				GLYPH_MAP.put("caret-square-o-left", '\uf191');
				break; // toggle-left ,
			case "dot-circle-o":
				GLYPH_MAP.put("dot-circle-o", '\uf192');
				break;
			case "wheelchair":
				GLYPH_MAP.put("wheelchair", '\uf193');
				break;
			case "vimeo-square":
				GLYPH_MAP.put("vimeo-square", '\uf194');
				break;
			case "try":
				GLYPH_MAP.put("try", '\uf195');
				break; // turkish-lira ,
			case "plus-square-o":
				GLYPH_MAP.put("plus-square-o", '\uf196');
				break;
			case "space-shuttle":
				GLYPH_MAP.put("space-shuttle", '\uf197');
				break;
			case "slack":
				GLYPH_MAP.put("slack", '\uf198');
				break;
			case "envelope-square":
				GLYPH_MAP.put("envelope-square", '\uf199');
				break;
			case "wordpress":
				GLYPH_MAP.put("wordpress", '\uf19a');
				break;
			case "openid":
				GLYPH_MAP.put("openid", '\uf19b');
				break;
			case "university":
				GLYPH_MAP.put("university", '\uf19c');
				break; // institution! bank ,
			case "graduation-cap":
				GLYPH_MAP.put("graduation-cap", '\uf19d');
				break; // mortar-board ,
			case "yahoo":
				GLYPH_MAP.put("yahoo", '\uf19e');
				break;
			case "google":
				GLYPH_MAP.put("google", '\uf1a0');
				break;
			case "reddit":
				GLYPH_MAP.put("reddit", '\uf1a1');
				break;
			case "reddit-square":
				GLYPH_MAP.put("reddit-square", '\uf1a2');
				break;
			case "stumbleupon-circle":
				GLYPH_MAP.put("stumbleupon-circle", '\uf1a3');
				break;
			case "stumbleupon":
				GLYPH_MAP.put("stumbleupon", '\uf1a4');
				break;
			case "delicious":
				GLYPH_MAP.put("delicious", '\uf1a5');
				break;
			case "digg":
				GLYPH_MAP.put("digg", '\uf1a6');
				break;
			case "pied-piper-pp":
				GLYPH_MAP.put("pied-piper-pp", '\uf1a7');
				break;
			case "pied-piper-alt":
				GLYPH_MAP.put("pied-piper-alt", '\uf1a8');
				break;
			case "drupal":
				GLYPH_MAP.put("drupal", '\uf1a9');
				break;
			case "joomla":
				GLYPH_MAP.put("joomla", '\uf1aa');
				break;
			case "language":
				GLYPH_MAP.put("language", '\uf1ab');
				break;
			case "fax":
				GLYPH_MAP.put("fax", '\uf1ac');
				break;
			case "building":
				GLYPH_MAP.put("building", '\uf1ad');
				break;
			case "child":
				GLYPH_MAP.put("child", '\uf1ae');
				break;
			case "paw":
				GLYPH_MAP.put("paw", '\uf1b0');
				break;
			case "spoon":
				GLYPH_MAP.put("spoon", '\uf1b1');
				break;
			case "cube":
				GLYPH_MAP.put("cube", '\uf1b2');
				break;
			case "cubes":
				GLYPH_MAP.put("cubes", '\uf1b3');
				break;
			case "behance":
				GLYPH_MAP.put("behance", '\uf1b4');
				break;
			case "behance-square":
				GLYPH_MAP.put("behance-square", '\uf1b5');
				break;
			case "steam":
				GLYPH_MAP.put("steam", '\uf1b6');
				break;
			case "steam-square":
				GLYPH_MAP.put("steam-square", '\uf1b7');
				break;
			case "recycle":
				GLYPH_MAP.put("recycle", '\uf1b8');
				break;
			case "car":
				GLYPH_MAP.put("car", '\uf1b9');
				break; // automobile ,
			case "taxi":
				GLYPH_MAP.put("taxi", '\uf1ba');
				break; // cab ,
			case "tree":
				GLYPH_MAP.put("tree", '\uf1bb');
				break;
			case "spotify":
				GLYPH_MAP.put("spotify", '\uf1bc');
				break;
			case "deviantart":
				GLYPH_MAP.put("deviantart", '\uf1bd');
				break;
			case "soundcloud":
				GLYPH_MAP.put("soundcloud", '\uf1be');
				break;
			case "database":
				GLYPH_MAP.put("database", '\uf1c0');
				break;
			case "file-pdf-o":
				GLYPH_MAP.put("file-pdf-o", '\uf1c1');
				break;
			case "file-word-o":
				GLYPH_MAP.put("file-word-o", '\uf1c2');
				break;
			case "file-excel-o":
				GLYPH_MAP.put("file-excel-o", '\uf1c3');
				break;
			case "file-powerpoint-o":
				GLYPH_MAP.put("file-powerpoint-o", '\uf1c4');
				break;
			case "file-image-o":
				GLYPH_MAP.put("file-image-o", '\uf1c5');
				break; // file-photo-o! file-picture-o ,
			case "file-archive-o":
				GLYPH_MAP.put("file-archive-o", '\uf1c6');
				break; // file-zip-o ,
			case "file-audio-o":
				GLYPH_MAP.put("file-audio-o", '\uf1c7');
				break; // file-sound-o ,
			case "file-video-o":
				GLYPH_MAP.put("file-video-o", '\uf1c8');
				break; // file-movie-o ,
			case "file-code-o":
				GLYPH_MAP.put("file-code-o", '\uf1c9');
				break;
			case "vine":
				GLYPH_MAP.put("vine", '\uf1ca');
				break;
			case "codepen":
				GLYPH_MAP.put("codepen", '\uf1cb');
				break;
			case "jsfiddle":
				GLYPH_MAP.put("jsfiddle", '\uf1cc');
				break;
			case "life-ring":
				GLYPH_MAP.put("life-ring", '\uf1cd');
				break; // life-bouy! life-buoy! life-saver! support ,
			case "circle-o-notch":
				GLYPH_MAP.put("circle-o-notch", '\uf1ce');
				break;
			case "rebel":
				GLYPH_MAP.put("rebel", '\uf1d0');
				break; // ra! resistance!
			case "empire":
				GLYPH_MAP.put("empire", '\uf1d1');
				break; // ge ,
			case "git-square":
				GLYPH_MAP.put("git-square", '\uf1d2');
				break;
			case "git":
				GLYPH_MAP.put("git", '\uf1d3');
				break;
			case "hacker-news":
				GLYPH_MAP.put("hacker-news", '\uf1d4');
				break; // y-combinator-square! yc-square ,
			case "tencent-weibo":
				GLYPH_MAP.put("tencent-weibo", '\uf1d5');
				break;
			case "qq":
				GLYPH_MAP.put("qq", '\uf1d6');
				break;
			case "weixin":
				GLYPH_MAP.put("weixin", '\uf1d7');
				break; // wechat ,
			case "paper-plane":
				GLYPH_MAP.put("paper-plane", '\uf1d8');
				break; // send ,
			case "paper-plane-o":
				GLYPH_MAP.put("paper-plane-o", '\uf1d9');
				break; // send-o ,
			case "history":
				GLYPH_MAP.put("history", '\uf1da');
				break;
			case "circle-thin":
				GLYPH_MAP.put("circle-thin", '\uf1db');
				break;
			case "header":
				GLYPH_MAP.put("header", '\uf1dc');
				break;
			case "paragraph":
				GLYPH_MAP.put("paragraph", '\uf1dd');
				break;
			case "sliders":
				GLYPH_MAP.put("sliders", '\uf1de');
				break;
			case "share-alt":
				GLYPH_MAP.put("share-alt", '\uf1e0');
				break;
			case "share-alt-square":
				GLYPH_MAP.put("share-alt-square", '\uf1e1');
				break;
			case "bomb":
				GLYPH_MAP.put("bomb", '\uf1e2');
				break;
			case "futbol-o":
				GLYPH_MAP.put("futbol-o", '\uf1e3');
				break; // soccer-ball-o ,
			case "tty":
				GLYPH_MAP.put("tty", '\uf1e4');
				break;
			case "binoculars":
				GLYPH_MAP.put("binoculars", '\uf1e5');
				break;
			case "plug":
				GLYPH_MAP.put("plug", '\uf1e6');
				break;
			case "slideshare":
				GLYPH_MAP.put("slideshare", '\uf1e7');
				break;
			case "twitch":
				GLYPH_MAP.put("twitch", '\uf1e8');
				break;
			case "yelp":
				GLYPH_MAP.put("yelp", '\uf1e9');
				break;
			case "newspaper-o":
				GLYPH_MAP.put("newspaper-o", '\uf1ea');
				break;
			case "wifi":
				GLYPH_MAP.put("wifi", '\uf1eb');
				break;
			case "calculator":
				GLYPH_MAP.put("calculator", '\uf1ec');
				break;
			case "paypal":
				GLYPH_MAP.put("paypal", '\uf1ed');
				break;
			case "google-wallet":
				GLYPH_MAP.put("google-wallet", '\uf1ee');
				break;
			case "cc-visa":
				GLYPH_MAP.put("cc-visa", '\uf1f0');
				break;
			case "cc-mastercard":
				GLYPH_MAP.put("cc-mastercard", '\uf1f1');
				break;
			case "cc-discover":
				GLYPH_MAP.put("cc-discover", '\uf1f2');
				break;
			case "cc-amex":
				GLYPH_MAP.put("cc-amex", '\uf1f3');
				break;
			case "cc-paypal":
				GLYPH_MAP.put("cc-paypal", '\uf1f4');
				break;
			case "cc-stripe":
				GLYPH_MAP.put("cc-stripe", '\uf1f5');
				break;
			case "bell-slash":
				GLYPH_MAP.put("bell-slash", '\uf1f6');
				break;
			case "bell-slash-o":
				GLYPH_MAP.put("bell-slash-o", '\uf1f7');
				break;
			case "trash":
				GLYPH_MAP.put("trash", '\uf1f8');
				break;
			case "copyright":
				GLYPH_MAP.put("copyright", '\uf1f9');
				break;
			case "at":
				GLYPH_MAP.put("at", '\uf1fa');
				break;
			case "eyedropper":
				GLYPH_MAP.put("eyedropper", '\uf1fb');
				break;
			case "paint-brush":
				GLYPH_MAP.put("paint-brush", '\uf1fc');
				break;
			case "birthday-cake":
				GLYPH_MAP.put("birthday-cake", '\uf1fd');
				break;
			case "area-chart":
				GLYPH_MAP.put("area-chart", '\uf1fe');
				break;
			case "pie-chart":
				GLYPH_MAP.put("pie-chart", '\uf200');
				break;
			case "line-chart":
				GLYPH_MAP.put("line-chart", '\uf201');
				break;
			case "lastfm":
				GLYPH_MAP.put("lastfm", '\uf202');
				break;
			case "lastfm-square":
				GLYPH_MAP.put("lastfm-square", '\uf203');
				break;
			case "toggle-off":
				GLYPH_MAP.put("toggle-off", '\uf204');
				break;
			case "toggle-on":
				GLYPH_MAP.put("toggle-on", '\uf205');
				break;
			case "bicycle":
				GLYPH_MAP.put("bicycle", '\uf206');
				break;
			case "bus":
				GLYPH_MAP.put("bus", '\uf207');
				break;
			case "ioxhost":
				GLYPH_MAP.put("ioxhost", '\uf208');
				break;
			case "angellist":
				GLYPH_MAP.put("angellist", '\uf209');
				break;
			case "cc":
				GLYPH_MAP.put("cc", '\uf20a');
				break;
			case "ils":
				GLYPH_MAP.put("ils", '\uf20b');
				break; // shekel! sheqel ,
			case "meanpath":
				GLYPH_MAP.put("meanpath", '\uf20c');
				break;
			case "buysellads":
				GLYPH_MAP.put("buysellads", '\uf20d');
				break;
			case "connectdevelop":
				GLYPH_MAP.put("connectdevelop", '\uf20e');
				break;
			case "dashcube":
				GLYPH_MAP.put("dashcube", '\uf210');
				break;
			case "forumbee":
				GLYPH_MAP.put("forumbee", '\uf211');
				break;
			case "leanpub":
				GLYPH_MAP.put("leanpub", '\uf212');
				break;
			case "sellsy":
				GLYPH_MAP.put("sellsy", '\uf213');
				break;
			case "shirtsinbulk":
				GLYPH_MAP.put("shirtsinbulk", '\uf214');
				break;
			case "simplybuilt":
				GLYPH_MAP.put("simplybuilt", '\uf215');
				break;
			case "skyatlas":
				GLYPH_MAP.put("skyatlas", '\uf216');
				break;
			case "cart-plus":
				GLYPH_MAP.put("cart-plus", '\uf217');
				break;
			case "cart-arrow-down":
				GLYPH_MAP.put("cart-arrow-down", '\uf218');
				break;
			case "diamond":
				GLYPH_MAP.put("diamond", '\uf219');
				break;
			case "ship":
				GLYPH_MAP.put("ship", '\uf21a');
				break;
			case "user-secret":
				GLYPH_MAP.put("user-secret", '\uf21b');
				break;
			case "motorcycle":
				GLYPH_MAP.put("motorcycle", '\uf21c');
				break;
			case "street-view":
				GLYPH_MAP.put("street-view", '\uf21d');
				break;
			case "heartbeat":
				GLYPH_MAP.put("heartbeat", '\uf21e');
				break;
			case "venus":
				GLYPH_MAP.put("venus", '\uf221');
				break;
			case "mars":
				GLYPH_MAP.put("mars", '\uf222');
				break;
			case "mercury":
				GLYPH_MAP.put("mercury", '\uf223');
				break;
			case "transgender":
				GLYPH_MAP.put("transgender", '\uf224');
				break; // intersex ,
			case "transgender-alt":
				GLYPH_MAP.put("transgender-alt", '\uf225');
				break;
			case "venus-double":
				GLYPH_MAP.put("venus-double", '\uf226');
				break;
			case "mars-double":
				GLYPH_MAP.put("mars-double", '\uf227');
				break;
			case "venus-mars":
				GLYPH_MAP.put("venus-mars", '\uf228');
				break;
			case "mars-stroke":
				GLYPH_MAP.put("mars-stroke", '\uf229');
				break;
			case "mars-stroke-v":
				GLYPH_MAP.put("mars-stroke-v", '\uf22a');
				break;
			case "mars-stroke-h":
				GLYPH_MAP.put("mars-stroke-h", '\uf22b');
				break;
			case "neuter":
				GLYPH_MAP.put("neuter", '\uf22c');
				break;
			case "genderless":
				GLYPH_MAP.put("genderless", '\uf22d');
				break;
			case "facebook-official":
				GLYPH_MAP.put("facebook-official", '\uf230');
				break;
			case "pinterest-p":
				GLYPH_MAP.put("pinterest-p", '\uf231');
				break;
			case "whatsapp":
				GLYPH_MAP.put("whatsapp", '\uf232');
				break;
			case "server":
				GLYPH_MAP.put("server", '\uf233');
				break;
			case "user-plus":
				GLYPH_MAP.put("user-plus", '\uf234');
				break;
			case "user-times":
				GLYPH_MAP.put("user-times", '\uf235');
				break;
			case "bed":
				GLYPH_MAP.put("bed", '\uf236');
				break; // hotel ,
			case "viacoin":
				GLYPH_MAP.put("viacoin", '\uf237');
				break;
			case "train":
				GLYPH_MAP.put("train", '\uf238');
				break;
			case "subway":
				GLYPH_MAP.put("subway", '\uf239');
				break;
			case "medium":
				GLYPH_MAP.put("medium", '\uf23a');
				break;
			case "y-combinator":
				GLYPH_MAP.put("y-combinator", '\uf23b');
				break; // yc ,
			case "optin-monster":
				GLYPH_MAP.put("optin-monster", '\uf23c');
				break;
			case "opencart":
				GLYPH_MAP.put("opencart", '\uf23d');
				break;
			case "expeditedssl":
				GLYPH_MAP.put("expeditedssl", '\uf23e');
				break;
			case "battery-full":
				GLYPH_MAP.put("battery-full", '\uf240');
				break; // battery-4! battery ,
			case "battery-three-quarters":
				GLYPH_MAP.put("battery-three-quarters", '\uf241');
				break; // battery-3 ,
			case "battery-half":
				GLYPH_MAP.put("battery-half", '\uf242');
				break; // battery-2 ,
			case "battery-quarter":
				GLYPH_MAP.put("battery-quarter", '\uf243');
				break; // battery-1 ,
			case "battery-empty":
				GLYPH_MAP.put("battery-empty", '\uf244');
				break; // battery-0 ,
			case "mouse-pointer":
				GLYPH_MAP.put("mouse-pointer", '\uf245');
				break;
			case "i-cursor":
				GLYPH_MAP.put("i-cursor", '\uf246');
				break;
			case "object-group":
				GLYPH_MAP.put("object-group", '\uf247');
				break;
			case "object-ungroup":
				GLYPH_MAP.put("object-ungroup", '\uf248');
				break;
			case "sticky-note":
				GLYPH_MAP.put("sticky-note", '\uf249');
				break;
			case "sticky-note-o":
				GLYPH_MAP.put("sticky-note-o", '\uf24a');
				break;
			case "cc-jcb":
				GLYPH_MAP.put("cc-jcb", '\uf24b');
				break;
			case "cc-diners-club":
				GLYPH_MAP.put("cc-diners-club", '\uf24c');
				break;
			case "clone":
				GLYPH_MAP.put("clone", '\uf24d');
				break;
			case "balance-scale":
				GLYPH_MAP.put("balance-scale", '\uf24e');
				break;
			case "hourglass-o":
				GLYPH_MAP.put("hourglass-o", '\uf250');
				break;
			case "hourglass-start":
				GLYPH_MAP.put("hourglass-start", '\uf251');
				break; // hourglass-1 ,
			case "hourglass-half":
				GLYPH_MAP.put("hourglass-half", '\uf252');
				break; // hourglass-2 ,
			case "hourglass-end":
				GLYPH_MAP.put("hourglass-end", '\uf253');
				break; // hourglass-3 ,
			case "hourglass":
				GLYPH_MAP.put("hourglass", '\uf254');
				break;
			case "hand-rock-o":
				GLYPH_MAP.put("hand-rock-o", '\uf255');
				break; // hand-grab-o ,
			case "hand-paper-o":
				GLYPH_MAP.put("hand-paper-o", '\uf256');
				break; // hand-stop-o ,
			case "hand-scissors-o":
				GLYPH_MAP.put("hand-scissors-o", '\uf257');
				break;
			case "hand-lizard-o":
				GLYPH_MAP.put("hand-lizard-o", '\uf258');
				break;
			case "hand-spock-o":
				GLYPH_MAP.put("hand-spock-o", '\uf259');
				break;
			case "hand-pointer-o":
				GLYPH_MAP.put("hand-pointer-o", '\uf25a');
				break;
			case "hand-peace-o":
				GLYPH_MAP.put("hand-peace-o", '\uf25b');
				break;
			case "trademark":
				GLYPH_MAP.put("trademark", '\uf25c');
				break;
			case "registered":
				GLYPH_MAP.put("registered", '\uf25d');
				break;
			case "creative-commons":
				GLYPH_MAP.put("creative-commons", '\uf25e');
				break;
			case "gg":
				GLYPH_MAP.put("gg", '\uf260');
				break;
			case "gg-circle":
				GLYPH_MAP.put("gg-circle", '\uf261');
				break;
			case "tripadvisor":
				GLYPH_MAP.put("tripadvisor", '\uf262');
				break;
			case "odnoklassniki":
				GLYPH_MAP.put("odnoklassniki", '\uf263');
				break;
			case "odnoklassniki-square":
				GLYPH_MAP.put("odnoklassniki-square", '\uf264');
				break;
			case "get-pocket":
				GLYPH_MAP.put("get-pocket", '\uf265');
				break;
			case "wikipedia-w":
				GLYPH_MAP.put("wikipedia-w", '\uf266');
				break;
			case "safari":
				GLYPH_MAP.put("safari", '\uf267');
				break;
			case "chrome":
				GLYPH_MAP.put("chrome", '\uf268');
				break;
			case "firefox":
				GLYPH_MAP.put("firefox", '\uf269');
				break;
			case "opera":
				GLYPH_MAP.put("opera", '\uf26a');
				break;
			case "internet-explorer":
				GLYPH_MAP.put("internet-explorer", '\uf26b');
				break;
			case "television":
				GLYPH_MAP.put("television", '\uf26c');
				break; // tv ,
			case "contao":
				GLYPH_MAP.put("contao", '\uf26d');
				break;
			case "500px":
				GLYPH_MAP.put("500px", '\uf26e');
				break;
			case "amazon":
				GLYPH_MAP.put("amazon", '\uf270');
				break;
			case "calendar-plus-o":
				GLYPH_MAP.put("calendar-plus-o", '\uf271');
				break;
			case "calendar-minus-o":
				GLYPH_MAP.put("calendar-minus-o", '\uf272');
				break;
			case "calendar-times-o":
				GLYPH_MAP.put("calendar-times-o", '\uf273');
				break;
			case "calendar-check-o":
				GLYPH_MAP.put("calendar-check-o", '\uf274');
				break;
			case "industry":
				GLYPH_MAP.put("industry", '\uf275');
				break;
			case "map-pin":
				GLYPH_MAP.put("map-pin", '\uf276');
				break;
			case "map-signs":
				GLYPH_MAP.put("map-signs", '\uf277');
				break;
			case "map-o":
				GLYPH_MAP.put("map-o", '\uf278');
				break;
			case "map":
				GLYPH_MAP.put("map", '\uf279');
				break;
			case "commenting":
				GLYPH_MAP.put("commenting", '\uf27a');
				break;
			case "commenting-o":
				GLYPH_MAP.put("commenting-o", '\uf27b');
				break;
			case "houzz":
				GLYPH_MAP.put("houzz", '\uf27c');
				break;
			case "vimeo":
				GLYPH_MAP.put("vimeo", '\uf27d');
				break;
			case "black-tie":
				GLYPH_MAP.put("black-tie", '\uf27e');
				break;
			case "fonticons":
				GLYPH_MAP.put("fonticons", '\uf280');
				break;
			case "reddit-alien":
				GLYPH_MAP.put("reddit-alien", '\uf281');
				break;
			case "edge":
				GLYPH_MAP.put("edge", '\uf282');
				break;
			case "credit-card-alt":
				GLYPH_MAP.put("credit-card-alt", '\uf283');
				break;
			case "codiepie":
				GLYPH_MAP.put("codiepie", '\uf284');
				break;
			case "modx":
				GLYPH_MAP.put("modx", '\uf285');
				break;
			case "fort-awesome":
				GLYPH_MAP.put("fort-awesome", '\uf286');
				break;
			case "usb":
				GLYPH_MAP.put("usb", '\uf287');
				break;
			case "product-hunt":
				GLYPH_MAP.put("product-hunt", '\uf288');
				break;
			case "mixcloud":
				GLYPH_MAP.put("mixcloud", '\uf289');
				break;
			case "scribd":
				GLYPH_MAP.put("scribd", '\uf28a');
				break;
			case "pause-circle":
				GLYPH_MAP.put("pause-circle", '\uf28b');
				break;
			case "pause-circle-o":
				GLYPH_MAP.put("pause-circle-o", '\uf28c');
				break;
			case "stop-circle":
				GLYPH_MAP.put("stop-circle", '\uf28d');
				break;
			case "stop-circle-o":
				GLYPH_MAP.put("stop-circle-o", '\uf28e');
				break;
			case "shopping-bag":
				GLYPH_MAP.put("shopping-bag", '\uf290');
				break;
			case "shopping-basket":
				GLYPH_MAP.put("shopping-basket", '\uf291');
				break;
			case "hashtag":
				GLYPH_MAP.put("hashtag", '\uf292');
				break;
			case "bluetooth":
				GLYPH_MAP.put("bluetooth", '\uf293');
				break;
			case "bluetooth-b":
				GLYPH_MAP.put("bluetooth-b", '\uf294');
				break;
			case "percent":
				GLYPH_MAP.put("percent", '\uf295');
				break;
			case "gitlab":
				GLYPH_MAP.put("gitlab", '\uf296');
				break;
			case "wpbeginner":
				GLYPH_MAP.put("wpbeginner", '\uf297');
				break;
			case "wpforms":
				GLYPH_MAP.put("wpforms", '\uf298');
				break;
			case "envira":
				GLYPH_MAP.put("envira", '\uf299');
				break;
			case "universal-access":
				GLYPH_MAP.put("universal-access", '\uf29a');
				break;
			case "wheelchair-alt":
				GLYPH_MAP.put("wheelchair-alt", '\uf29b');
				break;
			case "question-circle-o":
				GLYPH_MAP.put("question-circle-o", '\uf29c');
				break;
			case "blind":
				GLYPH_MAP.put("blind", '\uf29d');
				break;
			case "audio-description":
				GLYPH_MAP.put("audio-description", '\uf29e');
				break;
			case "volume-control-phone":
				GLYPH_MAP.put("volume-control-phone", '\uf2a0');
				break;
			case "braille":
				GLYPH_MAP.put("braille", '\uf2a1');
				break;
			case "assistive-listening-systems":
				GLYPH_MAP.put("assistive-listening-systems", '\uf2a2');
				break;
			case "american-sign-language-interpreting":
				GLYPH_MAP.put("american-sign-language-interpreting", '\uf2a3');
				break; // asl-interpreting ,
			case "deaf":
				GLYPH_MAP.put("deaf", '\uf2a4');
				break; // deafness! hard-of-hearing ,
			case "glide":
				GLYPH_MAP.put("glide", '\uf2a5');
				break;
			case "glide-g":
				GLYPH_MAP.put("glide-g", '\uf2a6');
				break;
			case "sign-language":
				GLYPH_MAP.put("sign-language", '\uf2a7');
				break; // signing ,
			case "low-vision":
				GLYPH_MAP.put("low-vision", '\uf2a8');
				break;
			case "viadeo":
				GLYPH_MAP.put("viadeo", '\uf2a9');
				break;
			case "viadeo-square":
				GLYPH_MAP.put("viadeo-square", '\uf2aa');
				break;
			case "snapchat":
				GLYPH_MAP.put("snapchat", '\uf2ab');
				break;
			case "snapchat-ghost":
				GLYPH_MAP.put("snapchat-ghost", '\uf2ac');
				break;
			case "snapchat-square":
				GLYPH_MAP.put("snapchat-square", '\uf2ad');
				break;
			case "pied-piper":
				GLYPH_MAP.put("pied-piper", '\uf2ae');
				break;
			case "first-order":
				GLYPH_MAP.put("first-order", '\uf2b0');
				break;
			case "yoast":
				GLYPH_MAP.put("yoast", '\uf2b1');
				break;
			case "themeisle":
				GLYPH_MAP.put("themeisle", '\uf2b2');
				break;
			case "google-plus-official":
				GLYPH_MAP.put("google-plus-official", '\uf2b3');
				break; // google-plus-circle ,
			case "font-awesome":
				GLYPH_MAP.put("font-awesome", '\uf2b4');
				break; // fa ,
			case "handshake-o":
				GLYPH_MAP.put("handshake-o", '\uf2b5');
				break;
			case "envelope-open":
				GLYPH_MAP.put("envelope-open", '\uf2b6');
				break;
			case "envelope-open-o":
				GLYPH_MAP.put("envelope-open-o", '\uf2b7');
				break;
			case "linode":
				GLYPH_MAP.put("linode", '\uf2b8');
				break;
			case "address-book":
				GLYPH_MAP.put("address-book", '\uf2b9');
				break;
			case "address-book-o":
				GLYPH_MAP.put("address-book-o", '\uf2ba');
				break;
			case "address-card":
				GLYPH_MAP.put("address-card", '\uf2bb');
				break; // vcard ,
			case "address-card-o":
				GLYPH_MAP.put("address-card-o", '\uf2bc');
				break; // vcard-o ,
			case "user-circle":
				GLYPH_MAP.put("user-circle", '\uf2bd');
				break;
			case "user-circle-o":
				GLYPH_MAP.put("user-circle-o", '\uf2be');
				break;
			case "user-o":
				GLYPH_MAP.put("user-o", '\uf2c0');
				break;
			case "id-badge":
				GLYPH_MAP.put("id-badge", '\uf2c1');
				break;
			case "id-card":
				GLYPH_MAP.put("id-card", '\uf2c2');
				break; // drivers-license ,
			case "id-card-o":
				GLYPH_MAP.put("id-card-o", '\uf2c3');
				break; // drivers-license-o ,
			case "quora":
				GLYPH_MAP.put("quora", '\uf2c4');
				break;
			case "free-code-camp":
				GLYPH_MAP.put("free-code-camp", '\uf2c5');
				break;
			case "telegram":
				GLYPH_MAP.put("telegram", '\uf2c6');
				break;
			case "thermometer-full":
				GLYPH_MAP.put("thermometer-full", '\uf2c7');
				break; // thermometer-4! thermometer ,
			case "thermometer-three-quarters":
				GLYPH_MAP.put("thermometer-three-quarters", '\uf2c8');
				break; // thermometer-3 ,
			case "thermometer-half":
				GLYPH_MAP.put("thermometer-half", '\uf2c9');
				break; // thermometer-2 ,
			case "thermometer-quarter":
				GLYPH_MAP.put("thermometer-quarter", '\uf2ca');
				break; // thermometer-1 ,
			case "thermometer-empty":
				GLYPH_MAP.put("thermometer-empty", '\uf2cb');
				break; // thermometer-0 ,
			case "shower":
				GLYPH_MAP.put("shower", '\uf2cc');
				break;
			case "bath":
				GLYPH_MAP.put("bath", '\uf2cd');
				break; // bathtub s15 ,
			case "podcast":
				GLYPH_MAP.put("podcast", '\uf2ce');
				break;
			case "window-maximize":
				GLYPH_MAP.put("window-maximize", '\uf2d0');
				break;
			case "window-minimize":
				GLYPH_MAP.put("window-minimize", '\uf2d1');
				break;
			case "window-restore":
				GLYPH_MAP.put("window-restore", '\uf2d2');
				break;
			case "window-close":
				GLYPH_MAP.put("window-close", '\uf2d3');
				break; // times-rectangle ,
			case "window-close-o":
				GLYPH_MAP.put("window-close-o", '\uf2d4');
				break; // times-rectangle-o ,
			case "bandcamp":
				GLYPH_MAP.put("bandcamp", '\uf2d5');
				break;
			case "grav":
				GLYPH_MAP.put("grav", '\uf2d6');
				break;
			case "etsy":
				GLYPH_MAP.put("etsy", '\uf2d7');
				break;
			case "imdb":
				GLYPH_MAP.put("imdb", '\uf2d8');
				break;
			case "ravelry":
				GLYPH_MAP.put("ravelry", '\uf2d9');
				break;
			case "eercast":
				GLYPH_MAP.put("eercast", '\uf2da');
				break;
			case "microchip":
				GLYPH_MAP.put("microchip", '\uf2db');
				break;
			case "snowflake-o":
				GLYPH_MAP.put("snowflake-o", '\uf2dc');
				break;
			case "superpowers":
				GLYPH_MAP.put("superpowers", '\uf2dd');
				break;
			case "wpexplorer":
				GLYPH_MAP.put("wpexplorer", '\uf2de');
				break;
			case "meetup":
				GLYPH_MAP.put("meetup", '\uf2e0');
				break;
			default:
				GLYPH_MAP.put(name, '\uf04d');
			}
			// TODO
			val = GLYPH_MAP.get(name);
		}
		return String.valueOf(val);
	}

}
