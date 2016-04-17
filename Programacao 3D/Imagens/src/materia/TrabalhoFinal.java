package materia;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class TrabalhoFinal {
	public BufferedImage removeBlue(BufferedImage img){
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				Color c = new Color(img.getRGB(j, i));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				if(b > r && b > g){
					r = 0;
					g = 0;
					b = 0;
				}
				Color c1 = new Color(r,g,b);
//				System.out.println(c1.getRed());
				out.setRGB(j, i, c1.getRGB());
			}
		}
		return out;
	}
	public BufferedImage crop (BufferedImage img){
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		int iInicial = 0;
		int jInicial= 0;
		int iFinal = 0;
		int jFinal = 0;
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				Color c = new Color(img.getRGB(j, i));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				if(b < r && b < g){
					if (iInicial < i && iInicial == 0)
						iInicial = i;
					if(jInicial < j && jInicial == 0 )
						jInicial = j;
					if(i < img.getHeight()/10 * 7.5){
						if(iFinal < i)
							iFinal = i;
						if(jFinal < j)
							jFinal = j;						
					}
				}
			}
		}
//		iInicial = iInicial - 30;
//		jInicial = jInicial - 30;
//		iFinal = iFinal + 40;
//		jFinal = jFinal + 40;
		for (int i = iInicial; i < iFinal; i++) {
			for (int j = jInicial; j < jFinal; j++) {
				Color c = new Color(img.getRGB(j, i));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				out.setRGB(j, i, new Color(r,g,b).getRGB());
			}
		}
		return out;
	}

}
