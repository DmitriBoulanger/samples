package de.dbo.samples.pico.simple.origin.DITest;

import java.io.*;
import java.text.*;
import java.util.*;
import org.picocontainer.*;

public class MovieFinderImpl implements MovieFinder{

    public void list(Object lister) {
        System.out.println("MovieFinder was listed by  " + lister);
    }

}