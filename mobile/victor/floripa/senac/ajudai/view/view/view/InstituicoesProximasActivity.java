package victor.floripa.senac.ajudai.view.view.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.model.object.InstituicaoDTO;
import victor.floripa.senac.ajudai.view.view.util.Constantes;

public class InstituicoesProximasActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    Activity activity;
    Location location;
    GoogleApiClient googleApiClient;
    double latitude;
    double longitude;
    LatLng minhaLocalizacao;
    CameraPosition cameraPosition;

    private final Context mContext;

    // flag para GPS status
    boolean isGPSEnabled = false;

    // flag para network status
    boolean isNetworkEnabled = false;

    // flag para GPS status
    boolean canGetLocation = false;

    // A distância mínima para mudar Atualizações em metros
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 metros

    // O tempo mínimo entre as atualizações em milissegundos
    private static final long MIN_TIME_BW_UPDATES = 0; // 1 minute=1000 * 60 * 1

    // Declarar a Location Manager
    protected LocationManager locationManager;

    private boolean abriuTelaPrimeiraVez = true;

    private List<Instituicao> listInstituicoes;

    List<InstituicaoDTO> instituicaoDTOList;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public InstituicoesProximasActivity() {
        mContext = this;
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituicoes_proximas);

        //listarTodasInstituicoes();
        //transformarEmLatLng(listInstituicoes);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

    private void transformarEmLatLng(List<Instituicao> list) {

    }

    private List<Instituicao> listarTodasInstituicoes() {
        AsyncHttpClient client = new AsyncHttpClient();
        listInstituicoes = new ArrayList<>();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "instituicao/listAll", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(activity, "Iniciando", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                //InstituicaoDTOList instituicaoDTOList = gson.fromJson(s, InstituicaoDTOList.class);

                Type collectionType = new TypeToken<List<InstituicaoDTO>>() {
                }.getType();
                instituicaoDTOList = gson.fromJson(s, collectionType);

                for (int i = 0; i < instituicaoDTOList.size(); i++) {
                    Instituicao instituicao = new Instituicao();
                    instituicao.setId(Long.parseLong(instituicaoDTOList.get(i).getId()));
                    instituicao.setNome(instituicaoDTOList.get(i).getNome());
                    instituicao.setDescricao(instituicaoDTOList.get(i).getDescricao());
                    instituicao.setCategoria(instituicaoDTOList.get(i).getCategoria());
                    instituicao.setCnpj(instituicaoDTOList.get(i).getCnpj());
                    instituicao.setFacebook(instituicaoDTOList.get(i).getFacebook());
                    instituicao.setInstagram(instituicaoDTOList.get(i).getInstagram());
                    instituicao.setEmail(instituicaoDTOList.get(i).getEmail());
                    instituicao.setWebsite(instituicaoDTOList.get(i).getWebsite());
                    instituicao.setIcone(instituicaoDTOList.get(i).getIcone());
                    instituicao.setDestaque(instituicaoDTOList.get(i).getFotoDestaque());

                    System.out.println("NOME: "+instituicaoDTOList.get(i).getNome());
                    System.out.println("CAT: "+instituicaoDTOList.get(i).getCategoria());
                    System.out.println("WS: "+instituicaoDTOList.get(i).getWebsite());
                    System.out.println("FB: "+instituicaoDTOList.get(i).getFacebook());
                    System.out.println("IC: "+instituicaoDTOList.get(i).getIcone());
                    System.out.println("EMAIL: "+instituicaoDTOList.get(i).getEmail());
                    System.out.println("DSC: "+instituicaoDTOList.get(i).getDescricao());
                    System.out.println("DESTAQUE: "+instituicaoDTOList.get(i).getFotoDestaque());

                    listInstituicoes.add(instituicao);
                }

                Toast.makeText(activity, "Sucesso: " + listInstituicoes.size(), Toast.LENGTH_SHORT).show();


            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
                mensagem.setTitle("Mensagem");
                mensagem.setPositiveButton("Erro ao se conectar com o SGDB" + error, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                    }

                });
                mensagem.show();
            }
        });
        return listInstituicoes;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocation();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng local = new LatLng(-27.5974698, -48.5445540);
        LatLng local2 = new LatLng(-27.5943433, -48.5445540);
        LatLng local3 = new LatLng(-27.5975234, -48.5788888);
        LatLng local5 = new LatLng(-27.5953454, -48.5442222);
        mMap.addMarker(new MarkerOptions()
                .position(local2)
                .title("Casa Irmão Joaquim")
                .snippet("Casa de longa permanência de idosos")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.idoso_map)));
        mMap.addMarker(new MarkerOptions()
                .position(local3)
                .title("Casa Lar Emaus")
                .snippet("Centro de atividades para crianças em tempo integral")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.crianca_map)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(local));


        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Location getLocation() {
        try {
            Toast.makeText(activity, "GPS1", Toast.LENGTH_SHORT).show();
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // obter o status GPS
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // obter o status da rede
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                //Nenhum provedor de rede está habilitado
                Toast.makeText(activity, "Nada", Toast.LENGTH_SHORT).show();
            } else {
                this.canGetLocation = true;
                // Primeiro localização obter de provedor de rede
                if (isNetworkEnabled) {
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.

                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            1,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            Toast.makeText(this, ""+location.getLatitude(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    Toast.makeText(activity, "GPS", Toast.LENGTH_SHORT).show();
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                Toast.makeText(this, "2: "+location.getLatitude(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (abriuTelaPrimeiraVez) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            minhaLocalizacao = new LatLng(latitude, longitude);
            mMap.addCircle(new CircleOptions().center(minhaLocalizacao).radius(100));
            cameraPosition = new CameraPosition.Builder().
                    target(minhaLocalizacao).zoom(12).bearing(80).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            abriuTelaPrimeiraVez = false;
            getJSON("https://maps.googleapis.com/maps/api/geocode/address=Rua+das+baleias+franca,341," +
                    "Florianopolis,SC &components=country:BR");
        } else if (abriuTelaPrimeiraVez == false){

        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(activity, "Sim", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {

    }
    public void getJSON(final String urlWebService) {
        System.out.println("URL: " + urlWebService);
        /*
         * As fetching the json string is a network operation
         * And we cannot perform a network operation in main thread
         * so we need an AsyncTask
         * The constrains defined here are
         * Void -> We are not passing anything
         * Void -> Nothing at progress update as well
         * String -> After completion it should return a string and it will be the json string
         * */
        class GetJSON extends AsyncTask<String, String, String> {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                    System.out.println(s);
            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(String... strings) {
                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String line = bufferedReader.readLine();

                    while (line != null) {
                        sb.append(line);
                        line = bufferedReader.readLine();

                    }
                    System.out.println("sb: "+sb.toString());
                    return sb.toString();
            } catch (Exception e) {
                    System.out.println("ERRO: " + e);
                    return null;
                }

            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
}
