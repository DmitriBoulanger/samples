package de.dbo.samples.pico.simple.origin.DITest;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;


public class DITest{
		public static void main(String[] args){

            //Two ways to test Inversion of Control in pico container
            //Both use Dependency Injection Patten.

			try {
				System.out.println("Constructor Injection indirectly:");
				MyMovieFinder mmf = new MyMovieFinder();
				mmf.testWithPico();
			} catch (Exception e) {
				
				e.printStackTrace();
			}

			System.out.println("constructor Injection directly:");
			MutablePicoContainer pico = new DefaultPicoContainer();
			pico.addComponent(MovieFinderImpl.class);
			pico.addComponent(MovieLister.class);
			MovieLister lister = (MovieLister) pico.getComponent(MovieLister.class);
			lister.listFinder();

			System.out.println("Construct Object without PicoContainer:");
			//String title = "Once Upon a Time in the West";
			MovieFinder finder = new MovieFinderImpl();
			lister = new MovieLister(finder);
			lister.listFinder();

			System.exit(0);

		}

}