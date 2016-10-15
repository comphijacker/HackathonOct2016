package SendingMoney;

/**
 * Hello world!
 *
 */


package byebyemoney;

import com.google.bitcoin.core.;
import com.google.bitcoin.store.;
import com.google.bitcoin.discovery.DnsDiscovery;
import java.util.concurrent.ExecutionException;
import java.io.File;
import java.math.BigInteger;

public class App
{
   public static void main( String[] args )
       throws BlockStoreException, AddressFormatException,
                   InterruptedException, ExecutionException
   {
       NetworkParameters params = NetworkParameters.prodNet();
       Wallet wallet = new Wallet(params);
       DumpedPrivateKey key = new DumpedPrivateKey(params,
               “L1vJHdDqQ5kcY5q4QoY124zD21UVgFe6NL2835mp8UgG2FNU94Sy”);
       wallet.addKey(key.getKey());
       BlockChain chain = new BlockChain(params, wallet,
               new MemoryBlockStore(params));
     PeerGroup peerGroup = new PeerGroup(params, chain);
     peerGroup.addPeerDiscovery(new DnsDiscovery(params));
     peerGroup.addWallet(wallet);
     peerGroup.start();
     peerGroup.downloadBlockChain();
     BigInteger balance = wallet.getBalance();
     System.out.println(“Wallet balance: ” + balance);
     Address destinationAddress = new Address(params,
              “1BTCorgHwCg6u2YSAWKgS17qUad6kHmtQW”);
     BigInteger fee=BigInteger.valueOf(10000);
     Wallet.SendRequest req = Wallet.SendRequest.to(
             destinationAddress,balance.subtract(fee));
     req.fee = fee;
     Wallet.SendResult result = wallet.sendCoins(peerGroup, req);
     if(result != null)
         {
             result.broadcastComplete.get();
             System.out.println("The money was sent!");
         }
     else
         {
             System.out.println(“Something went wrong sending the money.”);
         }
   }
}
