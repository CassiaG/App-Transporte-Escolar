package com.example.transporteescolar;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class Cmeni extends Activity{
	private Camera camera;
    private SurfaceView surfaceView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cmeni);
		
		Button btCliente = (Button) findViewById (R.id.btCliente);
		Button btcontrol = (Button) findViewById (R.id.btcontrol);
		Button btcont = (Button) findViewById (R.id.btcont);
		Button btendereco = (Button) findViewById (R.id.btendereco);
		Button btrelatorio = (Button) findViewById (R.id.btrelatorio);
		Button sair = (Button) findViewById (R.id.button1);
		Button maps = (Button) findViewById (R.id.button2);
	/*	Button foto = (Button) findViewById (R.id.button3);
		
				foto.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						//O código que solicita a abertura da aplicação nativa da câmera pode ser visto a seguir:
						
						File picsDir = Environment.getExternalStoragePublicDirectory(
								
								    Environment.DIRECTORY_PICTURES);
								
								File imageFile = new File(picsDir, "foto.jpg");
								
								 
								
								Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
								 
								i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
							
								startActivity(i); 

					//	startActivity(new Intent (Cmeni.this, CameraActivity.class));
					 * ______________________________
					 *  Uri uri = Uri.fromFile(new File("/sdcard/minhaaplicacao/hello_camera.jpg"));
 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
 intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
 startActivity(intent);
						
						
					}
				});
		*/
				maps.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						/*Uri urimaps = Uri.parse("https://www.google.com.br/maps/preview#!data=!1m4!1m3!1d2982299!2d-48.6355141!3d-22.5460521/‎‎");
						Intent IPagWeb = new Intent (Intent.ACTION_VIEW, urimaps);
						startActivity(IPagWeb);*/
						startActivity(new Intent (Cmeni.this, Rresponsaveis.class));
					}
				});
		

		sair.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (Cmeni.this, MainActivity.class));
				
			}
		});
btrelatorio.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent (Cmeni.this, Relatorio.class));
				
			}
		});
		
		btendereco.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent (Cmeni.this, Enderecos.class));
				
			}
		});
	
		btcont.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent (Cmeni.this, Contato.class));
				
			}
		});
		
		btCliente.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent (Cmeni.this, Cadastro.class));
				
			}
		}); 
		btcontrol.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent (Cmeni.this, Consulta.class));
				
			}
		});
		
	}
	
	
	public void mensagemExibir (String titulo, String texto)
	{
	AlertDialog.Builder mensagem = new AlertDialog.Builder(Cmeni.this);
	mensagem.setTitle(titulo);
	mensagem.setMessage(texto);
	mensagem.setNeutralButton("OK", null);
	mensagem.show();
	}
}
