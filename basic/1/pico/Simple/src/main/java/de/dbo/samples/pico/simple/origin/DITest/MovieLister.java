package de.dbo.samples.pico.simple.origin.DITest;

import java.io.*;
import java.text.*;
import java.util.*;
import org.picocontainer.*;

public class MovieLister{

    MovieFinder finder;

    public MovieLister(MovieFinder finder){
        this.finder = finder;
    }

    public void listFinder(){
        finder.list(this);
    }
}