package com.example.moneyfriend;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.Arrays;
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

    public DbMain(){

        String email = "yss6024@naver.com";
        String password = "Vlrmrkdl1!";
        emAuth = new emailAuth();

        //emAuth.createAccount(email,password);
        emAuth.signIn(email,password);
    }
/*
    public String getuid(){
        return emAuth.getuid();
    }*/



    void openAccount(int attendanceNumber, String ownerOfAccount, boolean savingsOrNot) //계좌 개설 함수
    {

        Account account = new Account(ownerOfAccount);
        if (savingsOrNot == true)
            db.collection("Info/Account/SavingsAccount").document("Account_"+attendanceNumber+ownerOfAccount).set(account);
        else
            db.collection("Info/Account/BankAccount").document("Account_"+attendanceNumber+ownerOfAccount).set(account);
    }

    void deposit(int attendanceNumber, String ownerOfAccount, double amount, String savingsOrBank) // 입금 함수
    {
        final DocumentReference accountRef;

        accountRef = db.collection("Info/Account/"+savingsOrBank+"Account").document("Account_"+attendanceNumber+ownerOfAccount);

        accountRef.update("balance", FieldValue.increment(amount));



        accountRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                DocumentSnapshot documentSnapshot= task.getResult();
                AccountLog log = new AccountLog(true,amount,Double.valueOf(documentSnapshot.get("balance").toString()),LocalDate.now(), LocalTime.now());
                addAccountLog(accountRef,log,savingsOrBank);
            }
        });
    }


    double withdraw(int attendanceNumber, String ownerOfAccount, double amount, String savingsOrBank) // 출금 함수
    {
        DocumentReference accountRef;

        accountRef = db.collection("Info/Account/"+savingsOrBank+"Account").document("Account_"+attendanceNumber+ownerOfAccount);

        accountRef.update("balance", FieldValue.increment(-amount));

        accountRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                DocumentSnapshot documentSnapshot= task.getResult();
                AccountLog log = new AccountLog(false,amount,Double.valueOf(documentSnapshot.get("balance").toString()),LocalDate.now(), LocalTime.now());
                addAccountLog(accountRef,log,savingsOrBank);
            }
        });

        return amount;
    }

    void addAccountLog(DocumentReference accountRef, AccountLog log, String savingsOrBank) // 계좌에 거래내역(Log)을 추가하는 함수
    {
        accountRef.collection("AccountDetails").document("Log_"+log.getDateOfTransaction()+"_"+log.getTimeOfTransaction()).set(log);
    }

    void checkAccount(boolean savingsOrNot){
        Account account = new Account(data.student.getName());
        if (savingsOrNot == true){
            db.collection("Info/Account/SavingsAccount").document("Account_"+data.student.getAttendanceNumber()+data.student.getName()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        boolean isEmpty = task.getResult().exists();
                        if(!isEmpty){
                            data.db.openAccount(data.student.getAttendanceNumber(),data.student.getName(),false);
                        }
                        else{
                            Log.d(Oscillator.TAG,"account is already");
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
                            data.db.openAccount(data.student.getAttendanceNumber(), data.student.getName(), false);
                        } else {
                            Log.d(Oscillator.TAG, "account is already");
                        }
                    }
                }
            });
        }
    }

    void updatebalance(){
        data.student.setSalary(0);


        db.collection("Info/Account/SavingsAccount")
                .document("Account_"+data.student.getAttendanceNumber()+data.student.getName())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentS) {
                if(documentS.exists()){
                    int sal1 = Integer.parseInt(documentS.get("balance").toString());
                    if(data.student.getSalary()==0){
                        data.student.setSalary(sal1);
                    }
                    else{
                        data.student.setSalary(data.student.getSalary()+sal1);
                    }
                    db.collection("User/Student/StudentList").document("Student_"+data.student.getAttendanceNumber()+data.student.getName()).update("salary",data.student.getSalary());
                }
            }
        });
        db.collection("Info/Account/BankAccount")
                .document("Account_" + data.student.getAttendanceNumber() + data.student.getName())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentS) {
                int sal2 = Integer.parseInt(documentS.get("balance").toString());
                if (data.student.getSalary() == 0) {
                    data.student.setSalary(sal2);
                } else {
                    data.student.setSalary(data.student.getSalary() + sal2);
                }
                db.collection("User/Student/StudentList").document("Student_" + data.student.getAttendanceNumber() + data.student.getName()).update("salary", data.student.getSalary());
            }
        });
    }


    void signUp (Student student) // 회원가입 함수
    {
        db.collection("User/Student/StudentList").document("Student_"+student.getAttendanceNumber()+student.getName()).set(student);
        //student.putuid(mAuth.getUid());

        //DocumentReference documentReference = db.collection("User/Student/StudentList").document("Student_"+student.getAttendanceNumber()+student.getName()).set(student);

        //documentReference.set(student);

    }

    //void calculateCreditScore () // 신용점수 계산 함수

    void applyForJob (JobApplicationForm form) // 직업 신청함수 _학생용
    {
        db.collection("Info/Form/FormList_JobApplication").document(form.getStudentName()+"_"+form.getAttendanceNumber()).set(form);
    }

    List<Form> getJobApplicationForms () // 직업 신청함수 _선생님용
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

    void suggestNewJob (NewJobSuggestionForm form) // 새직업 제안함수 _학생용
    {
        db.collection("Info/Form/FormList_NewJobSuggestion")
                .document(form.getTitle()+"_"+form.getStudentName()+"_"+form.getAttendanceNumber()).set(form);
    }

    List<Form> getNewJobSuggestionForms () // 새직업 제안함수 _선생님용
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

    void setJob(int attendanceNumber, String studentName, String jobName) // 직업 설정 함수
    {
        DocumentReference documentStudent = db.collection("User/Student/StudentList").document("Student_"+attendanceNumber+studentName);
        documentStudent.update("Job", jobName);
    }

    void paySalary (int attendanceNumber, String studentName) // 급여 지급 함수
    {
        getTaxRate(new GetObjectCallback<Double>() {
            @Override
            public void callback(Double object)
            {
                Log.d("hereO",object.toString());
                objectCallbackDB=object;
                Log.d("hereO",objectCallbackDB.toString());
            }
        },"소득세");

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



    void addJob (Job job) /// 직업 추가 함수
    {
        db.collection("Info/Job/JobList").document(job.getName()).set(job);
    }

    void editJob (Job job) /// 직업 수정 함수
    {
        deleteJob(job.getName());
        addJob(job);
    }

    void deleteJob (String jobName) /// 직업 삭제 함수
    {
        db.collection("Info/Job/JobList").document(jobName).delete();
    }

    List<String> getJobList(int minimumCreditScore) /// 직업 List 가져오기 함수
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



    void addNotice (String title, String content) /// 공지사항 추가 함수
    {
        Notice notice = new Notice(title,content, LocalDate.now(),LocalTime.now());
        db.collection("Info/Notice/NoticeList").document(title).set(notice);
    }

    void getNotice (@NonNull GetObjectCallback<String> Callback, String title) /// 공지사항 가져오기 함수
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

    void addRule (Rule rule) // 규칙 추가 함수
    {
        db.collection("Info/Rule/RuleList").document(rule.getChapter()+"_"+rule.getArticle()+"_"+rule.getParagraph()).set(rule);
    }

    void editRule (Rule rule) // 규칙 수정 함수
    {
        deleteJob(rule.getChapter()+"_"+rule.getArticle()+"_"+rule.getParagraph());
        addRule(rule);
    }

    void deleteRule(String RuleName) // 규칙 제거 함수
    {
        db.collection("Info/Rule/RuleList").document(RuleName).delete();
    }

    List<String> getRuleList () // 규칙 가져오기 함수
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
/*
    double getTaxRate(String taxName)
    {

        db.collection("Info/Tax/TaxList")
                .document(taxName)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        Log.d("here1",documentSnapshot.get("taxRate").toString());

                        taxRate = Double.valueOf(documentSnapshot.get("taxRate").toString());
                        Log.d("here2",String.valueOf(taxRate));
                    }
                });




        Log.d("here3",String.valueOf(taxRate));

        return taxRate;
    }

 */

    List<String> getNoticeList() /// 공지 List 가져오기 함수
    {
        List<String> list = new ArrayList<>();

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
    void loadUserInform(String name, int attendNum){
        db.collection("User/Student/StudentList").document("Student_"+attendNum+name)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String Name,school;
                        int classNumber,attendanceNumber;

                        Log.d(TAG,"succes : "+"Student_"+attendNum+name+" : "+documentSnapshot);

                        Name = name;
                        school = documentSnapshot.get("school").toString();
                        classNumber = Integer.parseInt(documentSnapshot.get("classNumber").toString());
                        attendanceNumber = attendNum;

                        data.student = new Student(Name, attendanceNumber, classNumber, school);

                    }
                });
    }

}