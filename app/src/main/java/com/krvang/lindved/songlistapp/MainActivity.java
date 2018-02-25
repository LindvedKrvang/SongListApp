package com.krvang.lindved.songlistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.krvang.lindved.songlistapp.be.Song;
import com.krvang.lindved.songlistapp.bll.BPMManager;
import com.krvang.lindved.songlistapp.bll.IBPMManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListView mListView;
    private List<Song> mSongs;
    private IBPMManager mBPMManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBPMManager = new BPMManager();
        instantiateSongs();

        //ListView - Comment in the two lines below to run the ListView.
//        setContentView(R.layout.list_view);
//        instantiateListView();
        //OBS: Remember to out comment RecyclerView!


        //RecyclerView - Comment in the two lines below to run the RecyclerView
        setContentView(R.layout.recycler_view);
        instantiateRecyclerView();
        //OBS: Remember to out comment ListView!
    }

    private void instantiateSongs(){
        mSongs = new ArrayList<>();
        mSongs.add(new Song("Song 1", "Artist 4", "2018", 90));
        mSongs.add(new Song("Song 2", "Artist 1", "2018", 245));
        mSongs.add(new Song("Song 3", "Artist 1", "2018", 170));
        mSongs.add(new Song("Song 4", "Artist 3", "2018", 120));
        mSongs.add(new Song("Song 5", "Artist 2", "2018", 150));
        mSongs.add(new Song("Song 6", "Artist 1", "2018", 90));
        mSongs.add(new Song("Song 7", "Artist 4", "2018", 140));
        mSongs.add(new Song("Song 8", "Artist 2", "2018", 130));
    }

    //----------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------
    //ListView Code
    //----------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------

    private void instantiateListView(){
        mListView = findViewById(R.id.listView);

        ListViewAdapter adapter = new ListViewAdapter();
        mListView.setAdapter(adapter);
    }

    private class ListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mSongs.size();
        }

        @Override
        public Object getItem(int position) {
            return mSongs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ListViewHolder holder;

            if(view == null){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item, null);
                holder = new ListViewHolder();
                holder.mTitle = view.findViewById(R.id.txtTitle);
                holder.mArtist = view.findViewById(R.id.txtArtist);
                holder.mYear = view.findViewById(R.id.txtYear);
                holder.mBPM = view.findViewById(R.id.txtBPM);

                view.setTag(holder);
            }else
                holder = (ListViewHolder) view.getTag();

            Song song = mSongs.get(position);
            holder.mTitle.setText(song.getTitle());
            holder.mArtist.setText(song.getArtist());
            holder.mYear.setText(song.getYear());
            int bpm = song.getBPM();
            holder.mBPM.setText(bpm + "");
            holder.mBPM.setTextColor(mBPMManager.getBPMColor(bpm));

            return view;
        }

    }
    private class ListViewHolder{

        TextView mTitle, mArtist, mYear, mBPM;
    }

    //----------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------
    //RecyclerView Code
    //----------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------

    private void instantiateRecyclerView(){
        mRecyclerView = findViewById(R.id.lstvSongs);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecycleViewAdapter adapter = new RecycleViewAdapter();
        mRecyclerView.setAdapter(adapter);
    }

    private class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitle, mArtist, mYear, mBPM;

        public RecycleViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
            super(layoutInflater.inflate(R.layout.song_list_item, parent, false));
            itemView.setOnClickListener(this);

            mTitle = itemView.findViewById(R.id.txtTitle);
            mArtist = itemView.findViewById(R.id.txtArtist);
            mYear = itemView.findViewById(R.id.txtYear);
            mBPM = itemView.findViewById(R.id.txtBPM);
        }

        public void bind(Song song){
            mTitle.setText(song.getTitle());
            mArtist.setText(song.getArtist());
            mYear.setText(song.getYear());
            int bpm = song.getBPM();
            mBPM.setText(bpm + "");
            mBPM.setTextColor(mBPMManager.getBPMColor(bpm));
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), mTitle.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    private class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

        @Override
        public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new RecycleViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(RecycleViewHolder holder, int position) {
            Song song = mSongs.get(position);
            holder.bind(song);
        }

        @Override
        public int getItemCount() {
            return mSongs.size();
        }
    }
}
