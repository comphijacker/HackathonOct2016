package ReceiveMoney;

/**
 * Hello world!
 *
 */

import com.google.bitcoin.core.;
import com.google.bitcoin.store.;
import com.google.bitcoin.discovery.DnsDiscovery;
import java.io.File;
import java.math.BigInteger;

public class App
{
   public static void main( String[] args ) throws BlockStoreException
   {
       NetworkParameters params = NetworkParameters.prodNet();
       Wallet wallet = new Wallet(params);
       ECKey key = new ECKey();
       System.out.println(“Public address: ” +
           key.toAddress(params).toString());
       System.out.println(“Private key: ” +
           key.getPrivateKeyEncoded(params).toString());
       wallet.addKey(key);
       File file = new File(“my-blockchain”);
       SPVBlockStore store=new SPVBlockStore(params, file);
       BlockChain chain = new BlockChain(params, wallet, store);
       PeerGroup peerGroup = new PeerGroup(params, chain);
       peerGroup.addPeerDiscovery(new DnsDiscovery(params));
       peerGroup.addWallet(wallet);
       peerGroup.start();
       peerGroup.downloadBlockChain();
       wallet.addEventListener(new AbstractWalletEventListener()
           {
               public void onCoinsReceived(Wallet wallet,
                    Transaction tx, BigInteger prevBalance,
                     BigInteger newBalance)
               {
                   System.out.println(“Hello Money! Balance: “
                       + newBalance + ” satoshis”);
               }
          });
         while(true){}
   }
}