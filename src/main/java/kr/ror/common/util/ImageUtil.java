package kr.ror.common.util;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Base64;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;

import org.springframework.util.Base64Utils;
public class ImageUtil {
	
    public static final int RATIO = 0;
    public static final int SAME = -1;
    
    public static BufferedImage resize(BufferedImage srcImg, int width, int height) throws IOException {
        
        int srcWidth = srcImg.getWidth(null);
        int srcHeight = srcImg.getHeight(null);
        
        int destWidth = -1, destHeight = -1;
        
        if (width == SAME) {
            destWidth = srcWidth;
        } else if (width > 0) {
            destWidth = width;
        }
        
        if (height == SAME) {
            destHeight = srcHeight;
        } else if (height > 0) {
            destHeight = height;
        }
        
        if (width == RATIO && height == RATIO) {
            destWidth = srcWidth;
            destHeight = srcHeight;
        } else if (width == RATIO) {
            double ratio = ((double)destHeight) / ((double)srcHeight);
            destWidth = (int)((double)srcWidth * ratio);
        } else if (height == RATIO) {
            double ratio = ((double)destWidth) / ((double)srcWidth);
            destHeight = (int)((double)srcHeight * ratio);
        }
        
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH); 
        int pixels[] = new int[destWidth * destHeight]; 
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth); 
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        } 
        BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB); 
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth); 
        
        return destImg;
    }
    
    public static BufferedImage merge(BufferedImage baseImg, BufferedImage overlayImg, int offsetX, int offsetY) throws IOException {
		
    	Graphics2D graphics = (Graphics2D) baseImg.getGraphics(); 
    	
    	graphics.drawImage(overlayImg, offsetX, offsetY, null);
    	graphics.dispose();
    	
    	return baseImg;
    }
    
   
    
    public static BufferedImage merge(BufferedImage baseImg, BufferedImage overlayImg, int offsetX, int offsetY, int width, int height) throws IOException {
		
    	Graphics2D graphics = (Graphics2D) baseImg.getGraphics(); 
    	graphics.drawImage(overlayImg, offsetX, offsetY, width, height, null);
    	graphics.dispose();
    	
    	return baseImg;
    }
    
    public static BufferedImage merge(BufferedImage baseImg, List<Map<String, Object>> params) {
    
    	Graphics2D graphics = (Graphics2D) baseImg.getGraphics(); 
    	
    	int pSize = params.size();
    	
    	Map<String, Object> item = null;
    	for(int i=0; i<pSize; i++) {
    		item = params.get(i);
    		if(item.get("width") != null && item.get("height") != null) {
    			graphics.drawImage((BufferedImage) item.get("file"), (int) item.get("offsetX"), (int)item.get("offsetY"), (int)item.get("width"), (int)item.get("height"), null);
    		}else {
    			graphics.drawImage((BufferedImage)item.get("file"), (int)item.get("offsetX"), (int)item.get("offsetY"), null);
    		}
    		
    	}
    	
    	graphics.dispose();
    	
    	return baseImg;
    }
    
    public static BufferedImage addTxtCenter(BufferedImage srcImg, String fontFamily, float fontSize, String txt, float offsetX, float offsetY, Color color, boolean bShadow) {
    	Graphics2D g2d = srcImg.createGraphics();
		
		
		Hashtable<TextAttribute, Object> map = new Hashtable<TextAttribute, Object>();
		
		map.put(TextAttribute.FAMILY, fontFamily);
        map.put(TextAttribute.SIZE, fontSize);
        
        
        AttributedString vanGogh = new AttributedString(txt, map);
        
        LineBreakMeasurer lineMeasurer = null;
        
        int paragraphStart = 0;
	    int paragraphEnd = 0;;
        
	    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    	
    	if (lineMeasurer == null) {
    		AttributedCharacterIterator paragraph = vanGogh.getIterator();
    		paragraphStart = paragraph.getBeginIndex();
    		paragraphEnd = paragraph.getEndIndex();
    		FontRenderContext frc = g2d.getFontRenderContext();
    		lineMeasurer = new LineBreakMeasurer(paragraph, frc);
    	}
    	
    	
    	float startX = offsetX;
    	
    	float breakWidth = (float)srcImg.getWidth() - (startX * 2);
    	float drawPosY = offsetY;
    	lineMeasurer.setPosition(paragraphStart);
    	
    	while (lineMeasurer.getPosition() < paragraphEnd) {
    		
    		int nextOffset = lineMeasurer.nextOffset(breakWidth);
    		int limit = nextOffset;
    		
    		int carriageReturn = txt.indexOf('\n',lineMeasurer.getPosition()+1);
    		if(nextOffset > (carriageReturn - lineMeasurer.getPosition()) && carriageReturn != -1) {
    			limit = carriageReturn - lineMeasurer.getPosition();
    		}
    		
    		TextLayout layout = lineMeasurer.nextLayout(breakWidth, lineMeasurer.getPosition() + limit, false);
    		float drawPosX = layout.isLeftToRight() ? startX : breakWidth - layout.getAdvance();
    		drawPosY += layout.getAscent()+3;
    		
    		if(bShadow) {
    			g2d.setColor(Color.black);
        		layout.draw(g2d, drawPosX+2, drawPosY+2);
    		}
    		
    		g2d.setColor(color);
    		layout.draw(g2d, drawPosX, drawPosY);
    		
    		
    		drawPosY += layout.getDescent() + layout.getLeading();
    		
    	}
    	
    	g2d.dispose();
    	
    	BufferedImage newImg = new BufferedImage(srcImg.getWidth(), (int)drawPosY, BufferedImage.TYPE_INT_RGB);
    	Graphics2D graphics = (Graphics2D) newImg.getGraphics(); 
    	graphics.setComposite(AlphaComposite.Src);
    	
//    	graphics.drawImage(srcImg, 0, 0, srcImg.getWidth(), (int)drawPosY, 0, 0, srcImg.getWidth(), (int)drawPosY + 20, null);
    	graphics.drawImage(srcImg, 0, 0, srcImg.getWidth(), (int)drawPosY, 0, 0, srcImg.getWidth(), (int)drawPosY, null);
    	graphics.dispose();
    	
    	return newImg;
    }
    
    public static BufferedImage addTxt(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int offsetX, int offsetY) {
        return addTxt(srcImg, fontFamily, fontSize , txt, offsetX, offsetY, Color.black, false, false);
    }
    
    public static BufferedImage addTxt(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int offsetX, int offsetY, Color color) {
        return addTxt(srcImg, fontFamily, fontSize , txt, offsetX, offsetY, color, false, false);
    }
    
    public static BufferedImage addTxt(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int offsetX, int offsetY, boolean bShadow, boolean bBold) {
        return addTxt(srcImg, fontFamily, fontSize , txt, offsetX, offsetY, Color.black, bShadow, bBold);
    }
    
    public static BufferedImage addTxt(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int offsetX, int offsetY, Color color, boolean bShadow, boolean bBold) {
    	
    	Graphics2D g2d=null;
    	g2d=srcImg.createGraphics();
    	
    	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    	
    	int fontWeight = Font.PLAIN;
    	
    	if(bBold) {
    		fontWeight = Font.BOLD;
    	}
    	
    	Font font = new Font(fontFamily, fontWeight, fontSize);
    	
    	g2d.setFont(font);
    	
    	if(bShadow) {
    		g2d.setColor(Color.black);
        	g2d.drawString(txt,offsetX+2,offsetY+2);
    	}
    	
    	g2d.setColor(color);
    	g2d.drawString(txt,offsetX,offsetY);
    	
    	g2d.dispose();
    	
    	return srcImg;
    }

    public static BufferedImage addTxtCenter(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int centerOffsetX, int offsetY) {
        return addTxtCenter(srcImg, fontFamily, fontSize , txt, centerOffsetX, offsetY, Color.black, false, false);
    }

    public static BufferedImage addTxtCenter(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int centerOffsetX, int offsetY, Color color) {
        return addTxtCenter(srcImg, fontFamily, fontSize , txt, centerOffsetX, offsetY, color, false, false);
    }

    public static BufferedImage addTxtCenter(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int centerOffsetX, int offsetY, boolean bShadow, boolean bBold) {
        return addTxtCenter(srcImg, fontFamily, fontSize , txt, centerOffsetX, offsetY, Color.black, bShadow, bBold);
    }

    public static BufferedImage addTxtCenter(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int centerOffsetX, int offsetY, Color color, boolean bShadow, boolean bBold) {
    	
    	AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		int fontWeight = Font.PLAIN;
    	if(bBold) {
    		fontWeight = Font.BOLD;
    	}
		Font font = new Font(fontFamily, fontWeight, fontSize);
		int textwidth = (int)(font.getStringBounds(txt, frc).getWidth());
				
		int imgWidth = srcImg.getWidth();
		centerOffsetX = centerOffsetX + (textwidth / 2) > imgWidth ? centerOffsetX = imgWidth - (textwidth / 2) : centerOffsetX;
		int offsetX = centerOffsetX - (textwidth / 2);
		offsetX = offsetX < 0 ? 0 : offsetX;
		
		return addTxt(srcImg, fontFamily, fontSize , txt, offsetX, offsetY, color, bShadow, bBold);
    }

    public static BufferedImage addTxtRight(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int rightOffsetX, int offsetY) {
        return addTxtRight(srcImg, fontFamily, fontSize , txt, rightOffsetX, offsetY, Color.black, false, false);
    }
    
    public static BufferedImage addTxtRight(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int rightOffsetX, int offsetY, Color color) {
        return addTxtRight(srcImg, fontFamily, fontSize , txt, rightOffsetX, offsetY, color, false, false);
    } 
    
    public static BufferedImage addTxtRight(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int rightOffsetX, int offsetY, boolean bShadow, boolean bBold) {
        return addTxtRight(srcImg, fontFamily, fontSize , txt, rightOffsetX, offsetY, Color.black, bShadow, bBold);
    }
    
    public static BufferedImage addTxtRight(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int rightOffsetX, int offsetY, Color color, boolean bShadow, boolean bBold) {
    	
    	AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		int fontWeight = Font.PLAIN;
    	if(bBold) {
    		fontWeight = Font.BOLD;
    	}
		Font font = new Font(fontFamily, fontWeight, fontSize);
		int textwidth = (int)(font.getStringBounds(txt, frc).getWidth());
		
		int imgWidth = srcImg.getWidth();
		rightOffsetX = rightOffsetX > imgWidth ? imgWidth : rightOffsetX;
		int offsetX = rightOffsetX - textwidth;
		offsetX = offsetX < 0 ? 0 : offsetX;
		
		return addTxt(srcImg, fontFamily, fontSize , txt, offsetX, offsetY, color, bShadow, bBold);
    } 
    
    
    public static BufferedImage addTxt(BufferedImage srcImg, List<Map<String, Object>> txtList) {
    	
    	Graphics2D g2d=null;
    	g2d=srcImg.createGraphics();
    	
    	Font font = null;
    	
    	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    	
    	int count = txtList.size();
    	Map<String,Object> txtItem = null;
    	
    	for(int i=0; i<count; i++) {
    		txtItem = txtList.get(i);
    		int fontStyle = Font.PLAIN;
    		if(txtItem.get("style") != null) {
    			fontStyle = (int) txtItem.get("style");
    		}
    		font = new Font((String)txtItem.get("font"), fontStyle, (int) txtItem.get("fontSize"));
    		g2d.setFont(font);
    		
    		int offsetX = (int)txtItem.get("offsetX");
    		int offsetY = (int)txtItem.get("offsetY");
    		String txt = (String)txtItem.get("txt");
    		
    		// 그림자 효과를 위해 추가
    		if(txtItem.get("shadow") != null && (boolean)txtItem.get("shadow")) {
    			g2d.setColor(Color.black);
        		g2d.drawString(txt, offsetX+2, offsetY+2);
    		}
    		
    		g2d.setColor((Color)txtItem.get("color"));
    		g2d.drawString(txt, offsetX, offsetY);
    	}
    	
    	g2d.dispose();
    	
    	return srcImg;
    }
    
    public static int getTxtWidth(String fontFamily, int fontSize, String txt, boolean bBold) {
        
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int fontWeight = Font.PLAIN;
        if(bBold) {
            fontWeight = Font.BOLD;
        }
        Font font = new Font(fontFamily, fontWeight, fontSize);
        return (int)(font.getStringBounds(txt, frc).getWidth());
    }
    
    public static String fileToString(File file) {
        String fileString = new String();
        FileInputStream inputStream =  null;
        ByteArrayOutputStream byteOutStream = null;
        try {
            inputStream = new FileInputStream(file);
            byteOutStream = new ByteArrayOutputStream();
        
    	int len = 0;
    	byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf)) != -1) {
                 byteOutStream.write(buf, 0, len);
            }
            byte[] fileArray = byteOutStream.toByteArray();
            fileString = Base64Utils.encodeToString(fileArray);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
        		if(inputStream != null) inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            try {
				if(byteOutStream != null) byteOutStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return fileString;
    }
    
    // outline test
    public static BufferedImage addTxtOutline(BufferedImage srcImg, String fontFamily, int fontSize , String txt, int offsetX, int offsetY, Color color) {
    	
    	Graphics2D g2d=null;
    	g2d=srcImg.createGraphics();
    	
    	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    	
    	Font font = new Font(fontFamily, Font.PLAIN, fontSize);
        
    	g2d.setFont(font);
    	
    	g2d.setColor(Color.black);
    	g2d.drawString(txt,offsetX+2,offsetY+2);
    	
    	g2d.setColor(color);
    	g2d.drawString(txt,offsetX,offsetY);
    	
    	
    	
    	
//    	TextLayout textTl = new TextLayout(txt, font, frc);
//        Shape outline = textTl.getOutline(null);
//        
//        
//    	g2d.setColor(Color.black);
//    	g2d.translate(offsetX, offsetY);
//    	g2d.setStroke(new BasicStroke(-1f));
//    	g2d.draw(outline);
    	
    	g2d.dispose();
    	
    	return srcImg;
    }
    
    public static BufferedImage createBlankImage(int iWidth, int iHeight, Color color) {
    	BufferedImage bi = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
    	
    	Graphics2D g2d = bi.createGraphics();
    	
    	g2d.setColor(color);
    	g2d.fillRect(0, 0, iWidth, iHeight);
    	g2d.dispose();
    	return bi;
    }
    
    public static BufferedImage createGradientImage(int iWidth, int iHeight, Color startColor, Color endColor) {
        BufferedImage bi = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2d = bi.createGraphics();
        
        GradientPaint gp = new GradientPaint(0, iHeight/2, startColor, iWidth, iHeight/2, endColor, true);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, iWidth, iHeight);
        g2d.dispose();
        return bi;
    }
    
    public static BufferedImage addRect(BufferedImage bi, int offsetX, int offsetY, int iWidth, int iHeight, Color color) {
    	Graphics2D g2d = bi.createGraphics();
    	g2d.setColor(color);
    	g2d.fillRect(offsetX, offsetY, iWidth, iHeight);
    	g2d.dispose();
    	return bi;
    }
    
    public static String imgToBase64String(BufferedImage img, String formatName) throws UncheckedIOException{
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, formatName, Base64.getEncoder().wrap(os));
            return os.toString(StandardCharsets.UTF_8.name());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }
    
    public static void writeImage(BufferedImage image, String fileSavePath) throws Exception {
    	ImageIO.write(image, fileSavePath.split("\\.")[fileSavePath.split("\\.").length -1], new File(fileSavePath));
    }
    
    
    public static void writeJpegImage(BufferedImage image, String fileSavePath) throws Exception {
		final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
		writer.setOutput(new FileImageOutputStream(new File(fileSavePath)));
		
		JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
		jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegParams.setCompressionQuality(1f);
		writer.write(null, new IIOImage(image, null, null), jpegParams);
		
		writer.dispose();
	}
    
    
    public static String writeJpegImage(BufferedImage image) throws Exception {
    	final ByteArrayOutputStream os = new ByteArrayOutputStream();
    	
		final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
		writer.setOutput(Base64.getEncoder().wrap(os));
		
		JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
		jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegParams.setCompressionQuality(1f);
		writer.write(null, new IIOImage(image, null, null), jpegParams);
		
		writer.dispose();
		os.close();
		
		return os.toString(StandardCharsets.UTF_8.name());
	}
	
    public static BufferedImage drawImage(File targetFile, int width, int height) throws Exception {
    	BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	
    	Graphics g = result.getGraphics();
    	g.drawImage(ImageIO.read(targetFile), 0, 0, width, height, null);
    	
    	g.dispose();
    	
    	return result;
    	
    }
    
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = output.createGraphics();
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
	    g2.setComposite(AlphaComposite.SrcAtop);
	    g2.drawImage(image, 0, 0, null);
	    g2.dispose();
	    return output;
	}
    
    
    public static  BufferedImage cropImage(BufferedImage src, int width, int height) {
        BufferedImage dest = src.getSubimage(0, 0, width, height);
        return dest; 
     }
    
    public static BufferedImage centerString(BufferedImage src , String fontFamily, int fontSize,int width, int height, String s , Color color, boolean bBold) {
    	
    	Graphics2D g = null;
    	
    	g = src.createGraphics();
    	
        int fontWeight = Font.PLAIN;
    	if(bBold) {
    		fontWeight = Font.BOLD;
    	}
        
        Font font = new Font(fontFamily, fontWeight, fontSize);
        
        FontMetrics fm = g.getFontMetrics();
        int x = (width - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (height - (fm.getAscent() + fm.getDescent())) / 2);
        
        g.setFont(font);
        g.setColor(color);
        g.drawString(s, x, y);
        
        g.dispose();
        
        return src;
    }

    public static BufferedImage addCircle(BufferedImage src, int x, int y, int radius, Color color) {

    	Graphics2D g = src.createGraphics();
    	
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        //g.setStroke(new BasicStroke(radius));
    	g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    	g.dispose();
        
        return src;
    }

    
    public static BufferedImage drawDashedLine(BufferedImage src , int x1, int y1, int x2, int y2, Color color) {
		BufferedImage dest =  drawDashedLine(src, x1, y1, x2, y2, color, null);
		return dest;
    }

    public static BufferedImage drawDashedLine(BufferedImage src , int x1, int y1, int x2, int y2, Color color, float[] dash) {
    	
    	Graphics2D g = src.createGraphics();
    	
    	if(dash == null) {
    		dash = new float[]{ 2.0f };
    	}
    	
    	BasicStroke stroke = new BasicStroke(0.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);

	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g.setStroke(stroke);
    	g.setColor(color);
    	g.drawLine(x1, y1, x2, y2);
        g.dispose();
        
        return src;
        
    }

    
    public static BufferedImage drawPolyLine(BufferedImage src , int[] x, int[] y, int count, Color color) {
    	return drawPolyLine(src , x, y, count, color, 1);
    }

    
    public static BufferedImage drawPolyLine(BufferedImage src , int[] x, int[] y, int count, Color color, float lineWidth) {
    	
    	Graphics2D g = src.createGraphics();

    	BasicStroke stroke = new BasicStroke(lineWidth);

	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g.setStroke(stroke);
    	g.setColor(color);
    	g.drawPolyline(x, y, count);
        g.dispose();
        
        return src;
        
    }

    
    public static BufferedImage fillPoly(BufferedImage src , int[] x, int[] y, int count, Color color) {
    	
    	Graphics2D g = src.createGraphics();

	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g.setColor(color);
    	g.fillPolygon(x, y, count);
        g.dispose();
        
        return src;
        
    }
    
    public static BufferedImage rotate(BufferedImage src, double degree) {

		BufferedImage bi = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
    	Graphics2D g = bi.createGraphics();
    	
    	AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(degree), src.getWidth()/2, src.getHeight()/2);

	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g.drawRenderedImage(src, at);
        g.dispose();

        return bi;
    }
}