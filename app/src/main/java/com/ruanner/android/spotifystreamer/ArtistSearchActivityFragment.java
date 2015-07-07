package com.ruanner.android.spotifystreamer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArtistSearchActivityFragment extends Fragment {

    public ArtistSearchActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_artist_search, container, false);



        return rootView;
    }

    public class ArtistResultAdapter extends BaseAdapter {
        List<ArtistResult> artistResults = new ArrayList<ArtistResult>();
        Context context;

        public ArtistResultAdapter(Context context, List<ArtistResult> artistResults) {
            this.context = context;
            this.artistResults = artistResults;
        }

        @Override
        public int getCount() {
            return artistResults.size();
        }

        @Override
        public ArtistResult getItem(int position) {
            return artistResults.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View artistView = inflater.inflate(R.layout.list_item_artist, parent, false);

            ArtistResult artistResult = getItem(position);
            ((TextView) artistView.findViewById(R.id.artistName)).setText(artistResult.name);

            ImageView artistImage = ((ImageView) artistView.findViewById(R.id.artistImage));

            new DownloadImageTask(artistImage).execute(artistResult.imageUrl);

            return artistView;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
