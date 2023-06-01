package com.sandeidevlab.File_management_System.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.sandeidevlab.File_management_System.Enity.File;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


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
    public String updateFile(File file) throws ExecutionException, InterruptedException {
        Firestore dbFirestore=FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture= (ApiFuture<WriteResult>) dbFirestore.collection(COLLECTION_NAME).document(file.getTittle());
        return collectionApiFuture.get().getUpdateTime().toString();
    }
    public static List<File> getProductsByCategory(String category) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = dbFirestore.collection(COLLECTION_NAME);

        Query query = collectionRef.whereEqualTo("folder", category);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        List<File> productList = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents) {
            File file = document.toObject(File.class);
            productList.add(file);
        }

        return productList;
    }


    public static String deleteFileByTittle(String tittle) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = dbFirestore.collection(COLLECTION_NAME);

        Query query = collectionRef.whereEqualTo("tittle", tittle);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        if (!documents.isEmpty()) {
            // Delete the document
            documents.get(0).getReference().delete();
            return "Document deleted";
        } else {
            // Document with the provided tittle does not exist
            return "Document not found";
        }
    }

}
