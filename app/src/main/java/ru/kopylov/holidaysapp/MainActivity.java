package ru.kopylov.holidaysapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ru.kopylov.holidaysapp.model.HolidaysListInfo;

public class MainActivity extends AppCompatActivity implements GetHolidaysData.AsyncResponse, Adapter.ListItemClickListener {

    private static final String TAG = "MainActivity";
    private HolidaysListInfo holidaysListInfo;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL url = new URL("https://calendarific.com/api/v2/holidays?api_key=422f774af6164a96e511f09b7d927c54f881b855&country=RU&year=2023");
            new GetHolidaysData(this).execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) {
        Log.d(TAG, "processFinish: " + output);

        try {
            JSONObject outputJSON = new JSONObject(output);
            JSONObject responseJSON = outputJSON.getJSONObject("response");
            JSONArray array = responseJSON.getJSONArray("holidays");

            int length = array.length();
            holidaysListInfo = new HolidaysListInfo(length);
            ArrayList<String> namesHolidays = new ArrayList<String>();

            for (int i = 0; i < length; i++) {
                JSONObject obj = array.getJSONObject(i);
                String name = obj.getString("name");
                JSONObject dateObj = obj.getJSONObject("date");
                String dateIso = dateObj.getString("iso");
                namesHolidays.add(name);
                holidaysListInfo.addHoliday(name, dateIso, i);
            }

            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new Adapter(holidaysListInfo, length, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        CharSequence text = holidaysListInfo.holidaysListInfo[clickedItemIndex].getHolidayName();

        if (toast != null) {
            toast.cancel();
        }

        toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}