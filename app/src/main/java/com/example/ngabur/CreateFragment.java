package com.example.ngabur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CreateFragment extends Fragment {
//
//    ImageView recipe_photo;
//    TextView recipe_user, recipe_category;
//    Button create_button;
//    EditText recipe_name, recipe_ingredient, recipe_time, recipe_serving, recipe_calories, recipe_video, recipe_direction;
//    String imageUrl, user;
//    Uri uri;
//    FirebaseAuth auth;
//    String[] category = {"Meat", "Vegetarian", "Noodle"};
//    AutoCompleteTextView autoCompleteTextView;
//    ArrayAdapter<String> adapterItems;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_create, container, false);
//
//        recipe_photo = view.findViewById(R.id.recipe_photo);
//        recipe_name = view.findViewById(R.id.recipe_name);
//        recipe_ingredient = view.findViewById(R.id.recipe_ingredient);
//        recipe_time = view.findViewById(R.id.recipe_time);
//        recipe_serving = view.findViewById(R.id.recipe_serving);
//        recipe_calories = view.findViewById(R.id.recipe_calories);
//        recipe_video = view.findViewById(R.id.recipe_video);
//        create_button = view.findViewById(R.id.create_button);
//        recipe_category = view.findViewById(R.id.recipe_category);
//        recipe_direction = view.findViewById(R.id.recipe_direction);
//        recipe_user = view.findViewById(R.id.recipe_user);
//
//        autoCompleteTextView = view.findViewById(R.id.category_cuy);
//        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.list_item, category);
//
//        autoCompleteTextView.setAdapter(adapterItems);
//
//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String Item = parent.getItemAtPosition(position).toString();
//                recipe_category.setText(Item.toString());
//                Toast.makeText(getActivity(), Item, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        auth = FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser = auth.getCurrentUser();
//
//        user = firebaseUser.getUid();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Registered Users");
//        reference.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ReadWriteUserDetails readWriteUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
//                if(readWriteUserDetails != null){
//                    String names = readWriteUserDetails.name;
//
//                    recipe_user.setText(names);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//
//        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>(){
//                    @Override
//                    public void onActivityResult(ActivityResult result){
//                        if(result.getResultCode() == Activity.RESULT_OK){
//                            Intent data = result.getData();
//                            uri = data.getData();
//                            recipe_photo.setImageURI(uri);
//
//                        }
//                        else{
//                            Toast.makeText(getActivity(), "No Image Selected", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//
//        recipe_photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent photoPicker = new Intent(Intent.ACTION_PICK);
//                photoPicker.setType("image/*");
//                activityResultLauncher.launch(photoPicker);
//            }
//        });
//
//        create_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveData();
//            }
//        });
//
//        return view;
//    }
//
//    public void saveData() {
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Recipe").child(uri.getLastPathSegment());
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setCancelable(false);
//        builder.setView(R.layout.progress_layout);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
//
//                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
//                while(!uriTask.isComplete());
//                Uri urlImage = uriTask.getResult();
//                imageUrl = urlImage.toString();
//                uploadData();
//                dialog.dismiss();
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                dialog.dismiss();
//            }
//        });
//    }
//
//    public void uploadData() {
//        String name = recipe_name.getText().toString();
//        String ingredient = recipe_ingredient.getText().toString();
//        String time = recipe_time.getText().toString();
//        String serving = recipe_serving.getText().toString();
//        String calories = recipe_calories.getText().toString();
//        String users = recipe_user.getText().toString();
//        String uid = auth.getUid();
//
//        String video = recipe_video.getText().toString();
//        String array[] = video.split("=", 2);
//        String array2 = array[1];
//        String video1 = array2;
//
//        String category = recipe_category.getText().toString();
//        String direction = recipe_direction.getText().toString();
//
//        DataClass dataClass = new DataClass(name, ingredient, time, imageUrl, serving, calories, video1, category, direction, user);
//
//        FirebaseDatabase.getInstance().getReference("Recipe").child(name).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(getActivity(), "Saved",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), Login.class);
//                    startActivity(intent);
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}