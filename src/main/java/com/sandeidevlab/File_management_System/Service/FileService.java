package com.sandeidevlab.File_management_System.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.sandeidevlab.File_management_System.Enity.File;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class FileService {
    private static String COLLECTION_NAME="Files";
    public List<File> getFileDetails()throws ExecutionException, InterruptedException{
        Firestore dbFirestore= FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences=dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator=documentReferences.iterator();
        List<File> fileList=new ArrayList<>();
        File file=null;
        while (iterator.hasNext()){
            DocumentReference documentReference1=iterator.next();
            ApiFuture<DocumentSnapshot> future=documentReference1.get();
            DocumentSnapshot  document=future.get();
            file=document.toObject(File.class);
            fileList.add(file);
        }
        return fileList;

    }
}
