package materia;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import materia.TrabalhoFinal;

public class Teste {
	private Exercicios ex;
	private TrabalhoFinal tf;
	private String path = "img/";
	private String pathFinal = "img/final/";
	private String pathFinalCrop = "img/final/crop/";
	Teste() {
		ex = new Exercicios();
		tf = new TrabalhoFinal();
	}

	private BufferedImage loadFile(String path) {
		BufferedImage out = null;
		try {
			out = ImageIO.read(new File(path));

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro Load File");
		}
		return out;
	}

	private boolean writeFile(String path, BufferedImage out, String name) {
		try {
			ImageIO.write(out, "png", new File(path + name + ".png"));
			System.out.println(path + name + ".png");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro Writing File");
		}
		return false;
	}
	private boolean writeFileNoExtension(String path, BufferedImage out, String name) {
		try {
			ImageIO.write(out, "png", new File(path + name));
			System.out.println(path + name);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro Writing File");
		}
		return false;
	}

	private void testeBright(String path, float intensity) {
		BufferedImage img = this.loadFile(path);
		BufferedImage out = this.ex.bright(img, intensity);
		this.writeFile(this.path, out, "bright");
		System.out.println("Teste Bright -- OK");
	}

	private void testeAdd(String path1, String path2) {
		BufferedImage img1 = this.loadFile(path1);
		BufferedImage img2 = this.loadFile(path2);
		BufferedImage out = this.ex.add(img1, img2);
		this.writeFile(this.path, out, "add");
		System.out.println("TesteAdd - OK");
	}

	private void testeEgaTable(String path) {
		BufferedImage img = this.loadFile(path);
		BufferedImage out = this.ex.egaTable(img);
		this.writeFile(this.path, out, "EgaTable");
		System.out.println("TesteEGBA -- OK");
	}

	private void testeGrayScale(String path) {
		BufferedImage img = this.loadFile(path);
		BufferedImage out = this.ex.grayscale(img);
		this.writeFile(this.path, out, "GrayScale");
		System.out.println("Teste Gray Scale -- OK");
	}

	private void testeLerp(String path1, String path2, float percent) {
		BufferedImage img1 = this.loadFile(path1);
		BufferedImage img2 = this.loadFile(path2);
		BufferedImage out = this.ex.lerp(img1, img2, percent);
		this.writeFile(this.path, out, "Lerp");
		System.out.println("Lerp -- OK");
	}

	private void testeLinha(String path, int x1, int y1, int x2, int y2) {
		BufferedImage img = this.loadFile(path);
		Color c = new Color(255, 255, 255);
		BufferedImage out = this.ex.linha(img, x1, y1, x2, y2, c);
		this.writeFile(this.path, out, "Linha");
		System.out.println("Linha -- OK");
	}

	private void testeSubtract(String path1, String path2) {
		BufferedImage img1 = this.loadFile(path1);
		BufferedImage img2 = this.loadFile(path2);
		BufferedImage out = this.ex.subtract(img1, img2);
		this.writeFile(this.path, out, "Subtract");
		System.out.println("Subtract -- OK");
	}

	private void testeThreshold(String path, int value) {
		BufferedImage img = this.loadFile(path);
		BufferedImage out = this.ex.threshold(img, value);
		this.writeFile(this.path, out, "threshold");
		System.out.println("Threshold -- OK");
	}

	private void testeKernel(String path) {
		BufferedImage img = this.loadFile(path);
		float[][] kernel = { { 0.0625f, 0.125f, 0.0625f },
							 { 0.125f, 0.25f, 0.125f }, 
							 { 0.0625f, 0.125f, 0.0625f } };
		BufferedImage out = this.ex.kernel(img, kernel);

		this.writeFile(this.path, out, "Kernel");
	}

	private void testeRemoveBlue(String path) {
		this.writeFile(this.pathFinal, this.tf.removeBlue(this.loadFile(path)) , "blue");
	}
	
	private BufferedImage testeRemoveBlue(BufferedImage img){
		return this.tf.removeBlue(img);
	}

	private void removeAllBlue(final File folder){

    for (final File fileEntry : folder.listFiles()) {
        if (!fileEntry.isDirectory()) {
        	
            this.writeFileNoExtension(this.pathFinal, this.tf.removeBlue(this.loadFile(fileEntry.getAbsolutePath())),fileEntry.getName());
        }
    }
}
	private BufferedImage testCrop(String path){
		BufferedImage img = this.loadFile(path);
		return tf.crop(img);
	}
	
	private void testCrop(BufferedImage img){
		BufferedImage img2 = this.tf.crop(img);
		this.writeFile(pathFinal, img2, "crop");
	}
	
	private void testCropAll(final File folder){
		for (final File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	            this.writeFileNoExtension(this.pathFinalCrop, this.testCrop(fileEntry.getAbsolutePath()),"crop"+fileEntry.getName());
	        }
	    }
	}

	private void testImg() {
		// this.testeAdd("img/cor/mario.jpg", "img/cor/sonic.jpg");
		// this.testeBright("img/cor/mario.jpg", 3);
		// this.testeEgaTable("img/cor/mario.jpg");
		// this.testeGrayScale("img/cor/mario.jpg");
		// this.testeLerp ("img/cor/mario.jpg", "img/cor/sonic.jpg", 0.5f);
		// this.testeLinha("img/cor/mario.jpg", 0, 0, 300, 300);
		// this.testeSubtract("img/pb/errosB1.png", "img/pb/errosB2.png");
		// this.testeThreshold("img/cor/mario.jpg", 255);
//		this.testeKernel("img/cor/puppy.png");
//		this.testeRemoveBlue("img/cabeca/1320.png");
//		this.testCrop(this.testeRemoveBlue(this.loadFile("img/cabeca/1320.png")));
		this.removeAllBlue(new File("img/cabeca/"));
		this.testCropAll(new File("img/final"));
//		this.testCrop("img/final/1020.png");
	}

	public static void main(String[] args) {
		System.out.println("Executando testes...");
		Teste t = new Teste();
		t.testImg();

	}

}
