/*
Copyright 2016 Team 6, CMPUT301, University of Alberta - All Rights Reserved.
You may use, distribute, and copy all or parts of this code under terms and conditions of
University of Alberta and the Code of Student Behavior.
You can find the copy of license at http://www.github.com/Team6/...
For further information, contact me at jzphung@ualberta.ca
 */

package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This is the main view class of LonelyTwitter project. <p> It handles all
 * user interactions as well as file manipulations. </p>
 * <pre> All the files are stored in the form of "json" files stored in Emulator,
 * accessible from Android Device Monitor. </pre>
 * <code> Pseudo code sample:
 * open some file ... <br>
 * attack some text ... <br>
 * close the file. <br>
 * </code>
 * <ul>
 * <li>an item</li>
 * <li>another item</li>
 * <li>yet another item</li>
 * <li>again another item</li>
 * </ul>
 * <ol>
 * <li>an item</li>
 * <li>another item</li>
 * <li>yet another item</li>
 * <li>again another item</li>
 * </ol>
 *
 * @author jzphung
 * @see NormalTweet
 * @see Tweet
 * @see java.io.FileNotFoundException
 * @since 1.0
 */
public class LonelyTwitterActivity extends Activity {

	/**
	 * This is the file name that is being saved / loaded and contains all the tweets.
	 * @see #loadFromFile()
	 * @see #saveInFile()
	 */
	private Activity activity = this;
	private static final String FILE_NAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;

	public ListView getOldTweetsList(){
		return oldTweetsList;
	}

	private Tweet tweet;

	/**
	 * Called when the activity is first created. It identifies the bodyText, oldTweetsList and
	 * saveButton from the xml design file using the ids.
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
		Button saveButton = (Button) findViewById(R.id.save);

		/**
		 * This android method is used to know when the saveButton on the activity screen is pressed.
		 * It will get the text the user entered in the text field and then save to it to tweetList.
		 * The adapter is then notified that data in the tweetList has been changed to update the
		 * list view oldTweetsList. Finally save the tweetsList to FILE_NAME(file.sav).
		 * @see tweetList
		 * @see oldTweetsList
		 * @see saveInFile
		 */
		saveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
                bodyText.setText("");

				Tweet newTweet = new NormalTweet(text);

				tweetList.add(newTweet);
				adapter.notifyDataSetChanged();

				saveInFile();
			}
		});

		/**
		 * This android method is used to know when the clearButton has been pressed. It will
		 * clear the tweetList and then save the cleared tweetList back to FILE_NAME(file.sav).
		 * @see tweetList
		 * @see saveInFile
		 */
		Button clearButton = (Button) findViewById(R.id.clear);
		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setResult(RESULT_OK);
				tweetList.clear();
                adapter.notifyDataSetChanged();
				saveInFile();
			}
		});

		oldTweetsList.setOnItemClickListener(new
				AdapterView.OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				tweet = (Tweet) tweetList.get(position);
				//Toast.makeText(getBaseContext(), tweet.toString() + "", Toast.LENGTH_SHORT).show();
				intent.putExtra("editTweet",tweet.getMessage());
				startActivity(intent);
			}
		});

	}

	/**
	 * This android method is called when the app first starts up. It will load the tweets from
	 * FILE_NAME(file.sav) and then set the adapter using the list view to display the data
	 */
	@Override
	protected void onStart() {
		super.onStart();
		loadFromFile();
		//tweet = (Tweet) tweetList.get(0);
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}

	/**
	 * This method loads the tweets from FILE_NAME (file.sav), and puts them in tweetList.
	 * @throws FileNotFoundException
	 * @exception RuntimeException
	 */
	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILE_NAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

			Gson gson = new Gson();

			// Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
			Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();

			tweetList = gson.fromJson(in,listType);

		} catch (FileNotFoundException e) {
			/* Create a brand new tweet list if we can't find the file. */
			tweetList = new ArrayList<Tweet>();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method saves the tweets to FILE_NAME (file.sav) using json
	 * @throws FileNotFoundException
	 * @exception RuntimeException
	 */
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILE_NAME, 0);

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

			Gson gson = new Gson();
			gson.toJson(tweetList, out);
			out.flush();

			fos.close();
		} catch (FileNotFoundException e) {
			/* Rethrow. */
			throw new RuntimeException(e);
		} catch (IOException e) {
			/* Rethrow. */
			throw new RuntimeException(e);
		}
	}
}