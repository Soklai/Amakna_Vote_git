package com.onizia.amaknavote;

import android.app.*;
import android.os.*;
import android.webkit.*;
import android.widget.*;
import android.graphics.*;
import android.view.*;
import android.widget.AdapterView.*;
import android.content.*;

public class MyWeb extends Activity implements OnClickListener
{
	private WebView ViewWeb;
	
	private String urlVote = ("http://www.amakna.net/user/vote2");
	private String urlLogin = ("http://www.amakna.net/login");
	private String urlAccueil = ("http://www.amakna.net/");
	private String urlPriveNet = ("https://serveur-prive.net/dofus/amakna-1-29-le-leader-des-serveurs-prive-dofus-1000-co-1530/vote");
	private String NomDeCompte;
	private String MotDePasse;
	
	private int VoteFinish = 0;
	
	private Button confirmeVote;
	private Button resetCompte;
	private Button option;
	
	private boolean opt;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		setContentView(R.layout.myweb);
		
		Intent I_MyWeb = getIntent();
		NomDeCompte = I_MyWeb.getStringExtra("User");
		MotDePasse = I_MyWeb.getStringExtra("Pass");
		
		ViewWeb = (WebView)findViewById(R.id.webview);
		confirmeVote = (Button)findViewById(R.id.confirmeVote);
        confirmeVote.setOnClickListener((OnClickListener)this);
		
		resetCompte = (Button)findViewById(R.id.resetCompte);
        resetCompte.setOnClickListener((OnClickListener)this);
		
		option = (Button)findViewById(R.id.option);
        option.setOnClickListener((OnClickListener)this);
		
		WebSettings webSettings = ViewWeb.getSettings();
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

		ViewWeb.getSettings().setJavaScriptEnabled(true);
		ViewWeb.getSettings().setDomStorageEnabled(true);

		ViewWeb.setWebViewClient(new WebViewClient()
			{

				@Override
				public void onPageStarted(WebView view, String url, Bitmap favicon)
				{
					super.onPageStarted(view, url, favicon);		

					url = view.getUrl();

					if( url.equals(urlAccueil))
					{
						ViewWeb.loadUrl(urlVote);
					}
					
				} // Fin onPageStarted

				@Override
				public void onPageFinished(WebView view, String url)
				{  
					super.onPageFinished(view, url);	
                    
					url = ViewWeb.getUrl();
					
					if ( url.equals(urlLogin))
					{
						view.loadUrl( "javascript:var NDC = document.forms[0].elements[0].value = '"+ NomDeCompte +"';");
						view.loadUrl( "javascript:var MDP = document.forms[0].elements[1].value = '"+ MotDePasse +"';");
						view.loadUrl( "javascript:var SUB = document.getElementById('sender').click();");
					}
					
					if( url.equals(urlPriveNet))
					{
						
						VoteFinish = 0;
						option.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
						confirmeVote.setVisibility(view.VISIBLE);
					    view.loadUrl( "javascript:var SUPR = document.getElementById('burger').remove();");
					    view.loadUrl( "javascript:var SUPR2 = document.getElementById('logo').remove();");
					    view.loadUrl( "javascript:var SUPR3 = document.getElementById('rechercher').remove();");
						view.loadUrl( "javascript:var SUPR4 = document.getElementById('nav').remove();");
						view.loadUrl( "javascript:var SUPR5 = document.getElementById('breadcrumb').remove();");
						view.loadUrl( "javascript:var SUPR6 = document.getElementById('choix').remove();");
						/* Pub Video */ view.loadUrl( "javascript:var SUPR8 = document.getElementById('gauche').remove();");
						/* Zone bleue*/ view.loadUrl( "javascript:var SUPR9 = document.getElementById('inform').remove();");
						/* Zone noir infos */ view.loadUrl( "javascript:var MDP1 = document.getElementById('informations').remove();");
					    view.loadUrl( "javascript:var MDP2 = document.getElementsById('description').remove();");
					} 
					
					if( VoteFinish == 0)
					{
						ViewWeb.loadUrl("javascript:var Vote = document.forms[0].elements[0].click();");
					} else
					     {
							 VoteFinish = 1;
							 confirmeVote.setVisibility(view.GONE);
							 option.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
						 }					 
				} // Fin onPageFinished     

			}); // Fin WebClient
			
		ViewWeb.loadUrl(urlLogin);
    }

	@Override
	public void onClick(View view)
	{
		if (view.getId() == R.id.resetCompte)
		{
			Intent I_MainActivity = new Intent().setClass(this, MainActivity.class);
			I_MainActivity.putExtra("Reset", true);
			startActivity(I_MainActivity);
			this.finish();
		}
		
		if (view.getId() == R.id.confirmeVote)
		{  
		    VoteFinish = 1;
			ViewWeb.loadUrl(urlVote);
			
			
			Intent I_AlarmWakeup = new Intent().setClass(this, AlarmWakeup.class);
			startActivity(I_AlarmWakeup);
		}
		
		if (view.getId() == R.id.option)
		{
			if( opt == false)
			{
				ViewWeb.setVisibility(view.GONE);
				confirmeVote.setVisibility(view.GONE);
				resetCompte.setVisibility(view.VISIBLE);
				opt = true;
				
			} else
			     {
					 ViewWeb.setVisibility(view.VISIBLE);
					 confirmeVote.setVisibility(view.VISIBLE);
					 resetCompte.setVisibility(view.GONE);
					 opt = false;
				 }	  
			
      } // Fin onClick
	  
   }
   
} 
