package com.example.easybazaar.service;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.jose4j.lang.BouncyCastleProviderHelp;
import org.jose4j.lang.JoseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private String publicKey="BJ0ql3ZPunisg4LJjh_VKyF7EoA0zkhgFBegQQg58UxK_WzDwgNZi9kKyDDzha-LAjMXXGHgEZpnSNVnEsWUiQs";
    private String privateKey = "KpnjpitXLZfq3dcErXZAH2G2iJKInBd5m-IeZXmlATc";
    private List<Subscription> subscriptions = new ArrayList<>();
    private PushService pushService;

    @PostConstruct
    private void init() throws GeneralSecurityException{
        //Security.addProvider(new BouncyCastleProvider());
        pushService = new PushService(publicKey,privateKey);
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void subscribe(Subscription subscription){
        System.out.printf("Subscribed to "+subscription.endpoint);
        this.subscriptions.add(subscription);
    }


    public void unsubscribe(String endPoint){

        System.out.println("UnSubscribing from "+endPoint);
        subscriptions = subscriptions.stream().filter(s -> !endPoint.equals(s.endpoint)).collect(Collectors.toList());

    }

    public void sendNotification(Subscription subscription,String messageJson){

        try{
            pushService.send(new Notification(subscription,messageJson));
        } catch (JoseException  | GeneralSecurityException | IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 15000)
    private void sendNotifications(){


    }
}
