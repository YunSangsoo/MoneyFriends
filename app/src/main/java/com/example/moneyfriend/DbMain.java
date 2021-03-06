package com.example.moneyfriend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.utils.Oscillator;

import com.example.moneyfriend.Form.Form;
import com.example.moneyfriend.Form.JobApplicationForm;
import com.example.moneyfriend.Form.NewJobSuggestionForm;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class DbMain
{

    Object objectCallbackDB="null";

    FirebaseFirestore db = FirebaseFirestore.getInstance();;
    emailAuth emAuth;

    public DbMain(String email,String password){

        emAuth = new emailAuth();

        emAuth.createAccount(email,password);

        //emAuth.signIn(email,password);
    }
    public DbMain(String email,String password, boolean check_admin){

        Log.d(TAG,"sign start");
        emAuth = new emailAuth();
            emAuth.signIn(email,password);
        Log.d(TAG,"sign end");
    }

/*
    public String getuid(){
        return emAuth.getuid();
    }*/



    void openAccount(int attendanceNumber, String ownerOfAccount, boolean savingsOrNot) //?????? ?????? ??????
    {
        if (savingsOrNot == true) {
            data.Saccount = new Account(ownerOfAccount);
            db.collection("Info/Account/SavingsAccount").document("Account_" + attendanceNumber + ownerOfAccount).set(data.Saccount);
        }
        else {
            data.Baccount = new Account(ownerOfAccount);
            db.collection("Info/Account/BankAccount").document("Account_" + attendanceNumber + ownerOfAccount).set(data.Baccount);
            deposit(data.student.getAttendanceNumber(),data.student.getName(),10000,"Bank");
            data.student.setSalary(10000);
        }
    }

    void deposit(int attendanceNumber, String ownerOfAccount, double amount, String savingsOrBank) // ?????? ??????
    {
        final DocumentReference accountRef;

        accountRef = db.collection("Info/Account/"+savingsOrBank+"Account").document("Account_"+attendanceNumber+ownerOfAccount);

        accountRef.update("balance", FieldValue.increment(amount));



        accountRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d(TAG,"?????? : " + documentSnapshot.toString());
                AccountLog log = new AccountLog(true,amount,(int)Float.parseFloat(documentSnapshot.get("balance").toString()),LocalDate.now(), LocalTime.now(),new Date(System.currentTimeMillis()));
                addAccountLog(accountRef,log,savingsOrBank);
            }
        });
    }


    double withdraw(int attendanceNumber, String ownerOfAccount, double amount, String savingsOrBank) // ?????? ??????
    {
        data.student.setCreditScore(10);
        DocumentReference accountRef;

        accountRef = db.collection("Info/Account/"+savingsOrBank+"Account").document("Account_"+attendanceNumber+ownerOfAccount);

        accountRef.update("balance", FieldValue.increment(-amount));

        accountRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                AccountLog log = new AccountLog(false,amount,(int)Float.parseFloat(documentSnapshot.get("balance").toString()),LocalDate.now(), LocalTime.now(),new Date(System.currentTimeMillis()));
                addAccountLog(accountRef,log,savingsOrBank);
            }
        });

        return amount;
    }

    void addAccountLog(DocumentReference accountRef, AccountLog log, String savingsOrBank) // ????????? ????????????(Log)??? ???????????? ??????
    {
        Log.d(TAG,"Timestamp : " + log.getTimestamp().toString());
        accountRef.collection("AccountDetails").document("Log_"+log.getDateOfTransaction()+"_"+log.getTimeOfTransaction()).set(log);
    }

    //????????? ??????????????? ??????????????? ???????????????
    void checkAccount(boolean savingsOrNot,TextView accNum,TextView accBal){
        if (savingsOrNot == true){
            db.collection("Info/Account/SavingsAccount").document("Account_"+data.student.getAttendanceNumber()+data.student.getName()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        boolean isEmpty = task.getResult().exists();
                        if(!isEmpty){
                            data.db.openAccount(data.student.getAttendanceNumber(),data.student.getName(),savingsOrNot);
                            accNum.setText(Integer.toString(data.Saccount.getAccountNumber()));
                            accBal.setText(Integer.toString(data.Saccount.getBalance()));

                        }
                        else{
                            DocumentSnapshot documentS = task.getResult();
                            data.Saccount = new Account(documentS.get("ownerOfAccount").toString());
                            data.Saccount.setBalance((int)Float.parseFloat(documentS.get("balance").toString()));
                            data.Saccount.setAccountNumber(Integer.parseInt(documentS.get("accountNumber").toString()));
                            accNum.setText(Integer.toString(data.Saccount.getAccountNumber()));
                            accBal.setText(Integer.toString(data.Saccount.getBalance()));
                        }
                    }
                }
            });
        }
        else {
            db.collection("Info/Account/BankAccount").document("Account_" + data.student.getAttendanceNumber() + data.student.getName()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        boolean isEmpty = task.getResult().exists();
                        if (!isEmpty) {
                            data.db.openAccount(data.student.getAttendanceNumber(), data.student.getName(), savingsOrNot);
                            accNum.setText(Integer.toString(data.Baccount.getAccountNumber()));
                            accBal.setText(Integer.toString(data.Baccount.getBalance()));
                        } else {
                            DocumentSnapshot documentS = task.getResult();
                            data.Baccount = new Account(documentS.get("ownerOfAccount").toString());
                            data.Baccount.setBalance((int)Float.parseFloat(documentS.get("balance").toString()));
                            data.Baccount.setAccountNumber(Integer.parseInt(documentS.get("accountNumber").toString()));
                            accNum.setText(Integer.toString(data.Baccount.getAccountNumber()));
                            accBal.setText(Integer.toString(data.Baccount.getBalance()));
                        }
                    }
                }
            });
        }
    }

    //????????? ??????????????? ???????????????
    void updatebalance(TextView view){
        data.student.setSalary(0);
        db.collection("Info/Account/SavingsAccount")
                .document("Account_"+data.student.getAttendanceNumber()+data.student.getName())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentS) {
                if(documentS.exists()){
                    int sal1 = (int)Float.parseFloat(documentS.get("balance").toString());
                    if(data.student.getSalary()==0){
                        data.student.setSalary(sal1);
                    }
                    else{
                        data.student.setSalary(data.student.getSalary()+sal1);
                    }
                    db.collection("User/Student/StudentList").document(data.email).update("salary",data.student.getSalary());
                    view.setText(Integer.toString(data.student.getSalary()));
                }
                else{
                    data.db.openAccount(data.student.getAttendanceNumber(),data.student.getName(),true);
                    view.setText(Integer.toString(data.student.getSalary()));
                }
            }
        });
        db.collection("Info/Account/BankAccount")
                .document("Account_" + data.student.getAttendanceNumber() + data.student.getName())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentS) {
                if(documentS.exists()){
                    int sal2 = (int)Float.parseFloat(documentS.get("balance").toString());
                    if (data.student.getSalary() == 0) {
                        data.student.setSalary(sal2);
                    } else {
                        data.student.setSalary(data.student.getSalary() + sal2);
                    }
                    db.collection("User/Student/StudentList").document(data.email).update("salary", data.student.getSalary());
                    view.setText(Integer.toString(data.student.getSalary()));
                }
                else{
                    data.db.openAccount(data.student.getAttendanceNumber(),data.student.getName(),false);
                    view.setText(Integer.toString(data.student.getSalary()));
                }
            }
        });
    }


    void signUp (Student student,String email) // ???????????? ??????
    {
        student.setemail(email);
        db.collection("User/Student/StudentList").document(email).set(student);
    }

    //void calculateCreditScore () // ???????????? ?????? ??????

    void applyForJob (JobApplicationForm form) // ?????? ???????????? _?????????
    {
        db.collection("Info/Form/FormList_JobApplication").document(form.getStudentName()+"_"+form.getAttendanceNumber()).set(form);
    }

    List<Form> getJobApplicationForms () // ?????? ???????????? _????????????
    {
        List<Form> list = new ArrayList<>();
        db.collection("Info/Form/FormList_JobApplication")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {

                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Form form = new JobApplicationForm
                                        (       document.get("studentName").toString(),
                                                Integer.valueOf(document.get("attendanceNumber").toString()),
                                                document.get("title").toString(),
                                                document.get("contents").toString(),
                                                document.get("jobName").toString(),
                                                document.get("contentsOfCertificate").toString() );

                                //Log.d("here",form.toString());
                                list.add(form);
                            }

                        }
                        else
                        {
                            Log.d("here!!!", "Error getting documents: ", task.getException());
                        }
                    }
                });


        return list;

    }

    void suggestNewJob (NewJobSuggestionForm form) // ????????? ???????????? _?????????
    {
        db.collection("Info/Form/FormList_NewJobSuggestion")
                .document(form.getTitle()+"_"+form.getStudentName()+"_"+form.getAttendanceNumber()).set(form);
    }

    List<Form> getNewJobSuggestionForms () // ????????? ???????????? _????????????
    {
        List<Form> list = new ArrayList<>();
        db.collection("Info/Form/FormList_NewJobSuggestion")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {

                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Form form = new NewJobSuggestionForm
                                        (       document.get("studentName").toString(),
                                                Integer.valueOf(document.get("attendanceNumber").toString()),
                                                document.get("title").toString(),
                                                document.get("contents").toString(),
                                                document.get("reasonOfSuggestion").toString(),
                                                Integer.valueOf(document.get("salary").toString()) );


                                list.add(form);
                            }

                        }
                        else
                        {
                            Log.d("here!!!", "Error getting documents: ", task.getException());
                        }
                    }
                });


        return list;

    }

    void setJob(int attendanceNumber, String studentName, String jobName) // ?????? ?????? ??????
    {
        DocumentReference documentStudent = db.collection("User/Student/StudentList").document("Student_"+attendanceNumber+studentName);
        documentStudent.update("Job", jobName);
    }

    void paySalary (int attendanceNumber, String studentName) // ?????? ?????? ??????
    {
        getTaxRate(new GetObjectCallback<Double>() {
            @Override
            public void callback(Double object)
            {
                Log.d("hereO",object.toString());
                objectCallbackDB=object;
                Log.d("hereO",objectCallbackDB.toString());
            }
        },"?????????");

        DocumentReference documentStudent = db.collection("User/Student/StudentList").document("Student_"+attendanceNumber+studentName);
        documentStudent.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {

                DocumentSnapshot documentSnapshot= task.getResult();
                Log.d("here",documentSnapshot.get("job").toString());
                DocumentReference documentJob =db.collection("Info/Job/JobList").document(documentSnapshot.get("job").toString());

                documentJob.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                    {

                        DocumentSnapshot documentSnapshot= task.getResult();

                        double salary = Double.valueOf(documentSnapshot.get("salary").toString());
                        salary=salary-salary*Double.valueOf(objectCallbackDB.toString());
                        Log.d("here",String.valueOf(salary));

                        deposit(attendanceNumber,studentName,salary,"Bank");
                    }
                });



            }
        });


    }



    void addJob (Job job) /// ?????? ?????? ??????
    {
        db.collection("Info/Job/JobList").document(job.getName()).set(job);
    }

    void editJob (String beforeName,Job job) /// ?????? ?????? ??????
    {
        deleteJob(beforeName);
        addJob(job);
    }

    void deleteJob (String jobName) /// ?????? ?????? ??????
    {
        db.collection("Info/Job/JobList").document(jobName).delete();
    }

    List<String> getJobList(int minimumCreditScore) /// ?????? List ???????????? ??????
    {
        List<String> list = new ArrayList<>();

        db.collection("Info/Job/JobList")
                .whereGreaterThan("minimumCreditScore",minimumCreditScore)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {

                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                list.add(document.getId());
                            }

                        }
                        else
                        {
                            Log.d("here!!!", "Error getting documents: ", task.getException());
                        }
                    }
                });

        return list;
    }



    void addNotice (String title, String content) /// ???????????? ?????? ??????
    {
        Notice notice = new Notice(title,content, LocalDate.now(),LocalTime.now());
        db.collection("Info/Notice/NoticeList").document(title).set(notice);
    }

    void modifyNotice (String title, String content) /// ???????????? ?????? ??????
    {
        Notice notice = new Notice(title,content, LocalDate.now(),LocalTime.now());
        db.collection("Info/Notice/NoticeList").document(title).update("content",notice.getContent());
        db.collection("Info/Notice/NoticeList").document(title).update("dateOfEnter",notice.getDateOfEnter());
        db.collection("Info/Notice/NoticeList").document(title).update("timeOfEnter",notice.getTimeOfEnter());
    }

    void getNotice (@NonNull GetObjectCallback<String> Callback, String title) /// ???????????? ???????????? ??????
    {
        db.collection("Info/Notice/NoticeList").document(title).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                    {
                        DocumentSnapshot documentSnapshot= task.getResult();
                        Callback.callback(documentSnapshot.get("content").toString());
                    }
                });

    }

    //???????????? ????????? ?????????
    public void testNotice(ArrayList<ArrayList<TextView>> frame) {

        db.collection("Info/Notice/NoticeList")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            int tmp=0;
                            ArrayList<TextView> next;

                            for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                            {
                                Notice input = new Notice(document.get("title").toString(),document.get("content").toString(),LocalDate.parse(document.get("dateOfEnter").toString()),LocalTime.parse(document.get("timeOfEnter").toString()));
                                data.NoticeList.add(input);

                                if(tmp>=frame.size())
                                    continue;
                                next = frame.get(tmp);
                                if(next.size()==0)
                                    continue;
                                TextView change_text = next.get(0);
                                if(change_text!=null)
                                    change_text.setText(document.get("title").toString());

                                change_text = next.get(1);
                                if(change_text!=null)
                                    change_text.setText(document.get("dateOfEnter").toString());

                                change_text = next.get(2);
                                if(change_text!=null)
                                    change_text.setText(document.get("timeOfEnter").toString());

                                change_text = next.get(3);
                                if(change_text!=null)
                                    change_text.setText(document.get("content").toString());

                                tmp++;
                            }
                        }
                });

    }
    //?????? ????????? ??????????????? ?????????
    public void testAccountlog(ArrayList<TextView> frame) {
        String bankcheck;
        String checkdetail;
        db.collection("Info/Account/BankAccount/Account_"+data.student.getAttendanceNumber()+data.student.getName()+"/AccountDetails")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        int tmp=0;
                        TextView next;

                        for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                        {
                            Log.d(TAG,"test account here!");
                            next = frame.get(tmp);
                            if(tmp==3)
                                break;
                            String change_text;
                            change_text=document.get("dateOfTransaction").toString()+"    ";//??????

                            String amount=document.get("depositOrWithdrawal").toString();//??????
                            boolean bool_amount = Boolean.parseBoolean(amount);
                            if(bool_amount)
                                change_text+="??????         ";//??????
                            else
                                change_text+="??????         ";//??????
                            change_text+=document.get("amount").toString();//??????
                            for(int i=document.get("amount").toString().length();i<18;i++)
                                change_text+=" ";
                            change_text+=document.get("balance").toString();//????????????
                            next.setText(change_text);
                            tmp++;
                        }
                        for(;tmp<3;tmp++){
                            next = frame.get(tmp);
                            next.setText(" ");
                        }
                    }
                });


    }

    void addRule (Rule rule) // ?????? ?????? ??????
    {
        db.collection("Info/Rule/RuleList").document(rule.getChapter()+"_"+rule.getArticle()+"_"+rule.getParagraph()).set(rule);
    }

    void editRule (Rule rule) // ?????? ?????? ??????
    {
        deleteJob(rule.getChapter()+"_"+rule.getArticle()+"_"+rule.getParagraph());
        addRule(rule);
    }

    void deleteRule(String RuleName) // ?????? ?????? ??????
    {
        db.collection("Info/Rule/RuleList").document(RuleName).delete();
    }

    List<String> getRuleList () // ?????? ???????????? ??????
    {
        List<String> list = new ArrayList<>();

        db.collection("Info/Rule/RuleList")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {

                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                list.add(document.getId());
                            }

                        }
                        else
                        {
                            Log.d("here!!!", "Error getting documents: ", task.getException());
                        }
                    }
                });

        return list;

    }

    void addTaxAct(Tax tax)
    {
        db.collection("Info/Tax/TaxList").document(tax.getName()).set(tax);
    }
    void editTaxRate (String taxName, double rate)
    {
        db.collection("Info/Tax/TaxList").document(taxName).update("taxRate", rate);
    }
    void deleteTaxAct(String taxName)
    {
        db.collection("Info/Tax/TaxList").document(taxName).delete();
    }

    void getTaxRate(@NonNull GetObjectCallback<Double> Callback, String taxName)
    {
        db.collection("Info/Tax/TaxList")
                .document(taxName)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        Callback.callback(Double.valueOf(documentSnapshot.get("taxRate").toString()));
                    }
                });
    }

    List<String> getNoticeList() /// ?????? List ???????????? ??????
    {
        ArrayList list = new ArrayList();

        db.collection("Info/Notice/NoticeList")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {

                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                list.add(document.getId());
                            }

                        }
                        else
                        {
                            Log.d("here!!!", "Error getting documents: ", task.getException());
                        }
                    }
                });

        return list;
    }
    //????????? ????????? ???, ???????????? ????????? ????????? DB?????? ?????????
    void loadUserInform(String email,LoginActivity context){
        db.collection("User/Student/StudentList").document(email)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String Name,school,classNumber,job;
                        int attendanceNumber,salary,creditscore;

                        Name = documentSnapshot.get("name").toString();
                        school = documentSnapshot.get("school").toString();
                        classNumber = documentSnapshot.get("classNumber").toString();
                        attendanceNumber = Integer.parseInt(documentSnapshot.get("attendanceNumber").toString());
                        salary = (int)Float.parseFloat(documentSnapshot.get("salary").toString());
                        creditscore =  (int)Float.parseFloat(documentSnapshot.get("creditScore").toString());

                        job = documentSnapshot.get("job").toString();


                        data.student = new Student(Name, attendanceNumber, classNumber, school);

                        data.student.setSalary(salary);
                        data.student.setCreditScore(creditscore);
                        data.student.setJob(job);

                        Intent intent = new Intent(context, MainActivity.class); //intent (move page from StartActivity to MainActivity)
                        context.dialog.cancel();
                        context.startActivity(intent);//activity ?????? ??????
                        context.overridePendingTransition(0, 0); // ???????????? ??? ??????????????? ????????? ?????? ???????????????.
                        context.finish();

                    }
                });
    }
    //??????????????? ?????? ????????? ???????????? ??????
    void setInvestmentrate(String name,TextView t){
        db.collection("/Info/Investment/saving").document(name)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        float rate = Float.parseFloat(documentSnapshot.get("rate").toString());
                        rate*=100;
                        t.setText(Float.toString(rate));
                    }
                });
    }

    //?????? ?????? ???????????? ??????
    public void setinvestment(String name, TextView principal1, TextView principal2,TextView interestrate, TextView expectedreceipt) {
        db.collection("/Info/Investment/saving").document(name)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        float rate = Float.parseFloat(documentSnapshot.get("rate").toString());
                        int date = (int)((Float.parseFloat(documentSnapshot.get("month_term").toString()))*30/7);
                        principal1.setText("?????? : " + date + "???");
                        interestrate.setText("????????? " + (rate*100) + "%");
                        db.collection("Info/Account/SavingsAccount").document("Account_" + data.student.getAttendanceNumber() + data.student.getName())
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        int bal = (int)Float.parseFloat(documentSnapshot.get("balance").toString());
                                        int exc = (int)(bal*(1+rate));
                                        principal2.setText("?????? : " + bal + "??????");
                                        expectedreceipt.setText("??????????????? : " + exc + "??????");

                                    }
                                });

                    }
                });
    }

    //?????????(?????????)?????? ????????? ??? ??????????????? ?????????.
    public void loadAdminInform(String email, String i_password, LoginActivity loginActivity) {

        db.collection("User/Teacher/TeacherList").document(email)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String pwd = documentSnapshot.get("password").toString();
                        if(i_password.compareTo(pwd)!=0)
                            return;

                        String Name,school,classNumber;

                        Name = documentSnapshot.get("name").toString();
                        school = documentSnapshot.get("school").toString();
                        classNumber = documentSnapshot.get("classNumber").toString();

                        data.admin = new Teacher(Name, classNumber, school);

                        Intent intent = new Intent(loginActivity, MainActivity.class); //intent (move page from StartActivity to MainActivity)
                        loginActivity.dialog.cancel();
                        loginActivity.startActivity(intent);//activity ?????? ??????
                        loginActivity.overridePendingTransition(0, 0); // ???????????? ??? ??????????????? ????????? ?????? ???????????????.
                        loginActivity.finish();

                    }
                });
    }
    public void getStudentList() {
        data.studentList = new ArrayList<Student>();
        db.collection("User/Student/StudentList")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                        {
                            Student input = new Student(document.get("name").toString(),Integer.parseInt(document.get("attendanceNumber").toString()),document.get("classNumber").toString(),document.get("school").toString());
                            input.setemail(document.get("email").toString());

                            input.setSalary((int)Float.parseFloat(document.get("salary").toString()));
                            input.setCreditScore((int)Float.parseFloat(document.get("creditScore").toString()));
                            input.setJob(document.get("job").toString());
                            data.studentList.add(input);
                        }
                    }
                });

    }
    public void joblist(){
        db.collection("Info/Job/JobList")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                        {
                            Job input = new Job(document.get("name").toString(),Integer.parseInt(document.get("salary").toString()),Integer.parseInt(document.get("minimumCreditScore").toString()));
                            data.jobList.add(input);
                        }
                    }
                });

    }
    public void changejob(String jobname, String email){
        Log.d(TAG,"choose email : " + email);
        db.collection("User/Student/StudentList").document(email).update("job",jobname);
    }

    public void inputmonpay(TextView monthlypay) {
        db.collection("Info/Job/JobList").document(data.student.getJob())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        monthlypay.setText(documentSnapshot.get("salary").toString()+"??????");
                    }
                });
    }
}