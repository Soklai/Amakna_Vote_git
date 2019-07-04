package com.onizia.amaknavote;

import android.app.*;
import android.os.*;
import android.webkit.*;
import android.widget.*;
import java.net.*;
import android.graphics.*;
import android.view.*;
import android.widget.AdapterView.*;
import android.preference.*;
import java.util.*;
import android.util.*;
import android.content.*;
import android.support.v4.app.*;

public class MainActivity extends Activity implements OnClickListener
{
	
	private ImageView image;
	
	public Button confirmeVote;
	public Button confirmeLogin;
	
	public EditText EditTextNdc;
	public EditText EditTextMdp;
	
	public TextView TextViewNdc;
	public TextView TextViewMdp;
	
	public View view;
	public WebView ViewWeb;
	
	public String url;
	public String urlRedi = ("http://www.amakna.net/");
	
	public String NomDeCompte;
	public String MotDePasse;
	private boolean compteErg;
	private boolean resetCompte;
	
	public static final String PREFS_GAME = "com.onizia.amaknavote";
	public static final String GAME_SCORE = "GameScore" ;   
    
    @Override    
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);	
		
		image = (ImageView)findViewById(R.id.imageView);
		this.image.setImageResource(R.drawable.icon);
		
		TextViewNdc = (TextView)findViewById(R.id.textViewNdc);
		EditTextNdc = (EditText)findViewById(R.id.editTextNdc);
		
		TextViewMdp = (TextView)findViewById(R.id.textViewMdp);
		EditTextMdp = (EditText)findViewById(R.id.editTextMdp);
		
		confirmeLogin = (Button)findViewById(R.id.confirmeLogin);
        confirmeLogin.setOnClickListener((OnClickListener)this);	
		
		loadData();
		
		Intent I_Erg = getIntent();
		resetCompte = I_Erg.getBooleanExtra("Reset", false);
		
		if(resetCompte == true)
		{
		  resetCompte= false;
		  compteErg = false;
		  saveData();
			
		} else
		    {
				compteEnregistrer();
			}
	    
				  	 
	} //Fin onCreate

	@Override
	public void onClick(View view)
	{
		NomDeCompte = EditTextNdc.getText().toString();
		MotDePasse = EditTextMdp.getText().toString();
		compteErg = true;
		
		saveData();
		compteEnregistrer();
	}
    
    public void compteEnregistrer()
    {  
	   if( compteErg == true)
	   {     
		   Intent I_MyWeb = new Intent().setClass(this, MyWeb.class);
		   I_MyWeb.putExtra("User", NomDeCompte);
		   I_MyWeb.putExtra("Pass", MotDePasse);
		   startActivity(I_MyWeb);
		   this.finish();		      
	   }  
    }
   
    public void saveData()
    { 
		  SharedPreferences sp = getSharedPreferences ( PREFS_GAME , Context . MODE_PRIVATE ); 
		  
		  sp.edit().putBoolean("K_compteErg", compteErg).commit();
		  sp.edit().putString("K_Ndc", NomDeCompte).commit();
		  sp.edit().putString("K_Mdp", MotDePasse).commit();   
    }
   
    public void loadData()
    {
       SharedPreferences sp = getSharedPreferences ( PREFS_GAME , Context . MODE_PRIVATE );
	   compteErg = sp.getBoolean("K_compteErg", false);
       NomDeCompte = sp.getString("K_Ndc", "null");
	   MotDePasse = sp.getString("K_Mdp", "null"); 
    } 
    
} // Fin Class
