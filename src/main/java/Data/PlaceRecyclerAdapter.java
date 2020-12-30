package Data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaltry.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import Activity.DetailsActivity;
import Activity.DownloadImageTask;
import Model.Place;
import Model.User;

public class PlaceRecyclerAdapter  extends RecyclerView.Adapter<PlaceRecyclerAdapter.ViewHolder>  {
    private Context context;
    private List<Place> placeList;
private String url;
    public PlaceRecyclerAdapter(Context context, List<Place> placeList) {
        this.context = context;
        this.placeList = placeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_row,parent,false);
        return new ViewHolder(view , context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place place = placeList.get(position);
        String imageUrl = null;

        holder.title.setText(place.getTitle());

        holder.location.setText(place.getLocation());
       // holder.capacity.setText(place.getCapacity());
        holder.type.setText(place.getType());
      //  holder.desc.setText(place.getDec());

        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        String formatedDate = dateFormat.format(new Date(Long.valueOf(place.getTimeStamp())).getTime());
       // holder.timeStamp.setText(formatedDate);

        imageUrl = place.getImage();
url=imageUrl;
        // Use picaso
        Picasso.with(context)
                .load(imageUrl)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public TextView type;
        public TextView capacity;
        public TextView location;
        public TextView timeStamp;
        public ImageView image;
        private DatabaseReference mDatabaseReference;
        private FirebaseDatabase mDatabase;
        String userid;

        public ViewHolder( View view , Context cxt) {
            super(view);
            context = cxt;
            title = (TextView) view.findViewById(R.id.placeTitleList);
            //desc = (TextView) view.findViewById(R.id.placeDesList);
            type = (TextView) view.findViewById(R.id.placeTypeList);
            //capacity = (TextView) view.findViewById(R.id.placeCapacityList);
            location = (TextView) view.findViewById(R.id.placeLocationList);
            //timeStamp = (TextView) view.findViewById(R.id.placeTimestampList);
            image = (ImageView) view.findViewById(R.id.placeImageList);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            mDatabase = FirebaseDatabase.getInstance();
            userid = null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // we can go to next
                    int postion = getAdapterPosition();
                    Place place = placeList.get(postion);
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("title",place.getTitle());
                    intent.putExtra("desc",place.getDec());
                    intent.putExtra("type",place.getType());
                    intent.putExtra("capacity",place.getCapacity());
                    intent.putExtra("location",place.getLocation());
                    intent.putExtra("image",place.getImage());
                    intent.putExtra("id",place.getId());
                    intent.putExtra("price",place.getPrice());
                    context.startActivity(intent);

                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int postion = getAdapterPosition();
                    Place place = placeList.get(postion);
                    //DatabaseReference   mDatabaseReference1 = FirebaseDatabase.getInstance();
                    //mDatabaseReference1.removeValue();
                    Toast.makeText(context,place.getId(),Toast.LENGTH_LONG).show();
                    return false;
                }
            });
         /*   mDatabaseReference = mDatabase.getReference().child("Users");
            mDatabaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User uu = snapshot.getValue(User.class);
                    if (user != null) {

                        Boolean isadmin = uu.isAdmin();
                        if(isadmin){

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/


        }
    }
}
