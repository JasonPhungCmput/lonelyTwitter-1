package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditTweetActivity extends Activity {

    private ListView editTweetsList;
    private ArrayList<String> editTweetList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    String editTweet;

    public ListView getEditTweetsList(){
        return editTweetsList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);
        editTweetsList = (ListView) findViewById(R.id.editTweetsList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        editTweet = extras.getString("editTweet");
        Toast.makeText(getBaseContext(),editTweet + "", Toast.LENGTH_SHORT).show();
        editTweetList.add(editTweet);
        adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, editTweetList);
        editTweetsList.setAdapter(adapter);
    }

}
