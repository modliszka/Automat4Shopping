package NeuralNetworkHelper;

//
import java.awt.EventQueue;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;


import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
//

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;


public class FileHelper
{
public static boolean ChangesInDirectorySieciNeuronowe()
	{
  	  try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
 
            Path logDir = Paths.get("C:/Users/Micha³/Documents/GitHub/Automat4Shopping/src/NeuralNetworkHelper");
            logDir.register(watcher,
                    ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
 
            while (true) {
                WatchKey key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
 
                    if (kind == ENTRY_CREATE) {
                        System.out.println("Entry was created on log dir.");
                        return true;
                    } else if (kind == ENTRY_MODIFY) {
                        System.out.println("Entry was modified on log dir.");
                        return true;
                       
                    } else if (kind == ENTRY_DELETE) {
                        System.out.println("Entry was deleted from log dir.");
                        
                    }
                }
                key.reset();
                
            }
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
  	return false;
	}
	
	

}

