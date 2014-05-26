package de.dbo.samples.image.houghtransform.data;

/**
 * Declaration of available image collections for development and JUnit-tests.
 * Images are allocated as test-resources in src/test/resources/
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and
 *           only incidentally for computers to execute
 *
 */
public enum ImageCollectionCatalog {
	NULL

    // SIGNATURE ----------------------------------------------------------------

    ,SIGNATURE_TEST0("signature/test/0/")
    , SIGNATURE_TEST1("signature/test/1/")
    , SIGNATURE_TEST2("signature/test/2/")
    , SIGNATURE_PROBLEM("signature/test/problem/")

    , SIGNATURE_PERFORMANCE_CHECKED("signature/")
    , SIGNATURE_PERFORMANCE_UNCHECKED("signature/")

    , SIGNATURE_PERFORMANCE_ERROR_CHECKED("signature/")
    , SIGNATURE_PERFORMANCE_ERROR_UNCHECKED("signature/")

    // BOX --------------------------------------------------------------------

    , BOX_TEST0("box/test/0/")
    , BOX_TEST1("box/test/1/")
    , BOX_FEATURES("box/features/")
    , BOX_SAMPLES_NORMAL0("box/samples/normal/0/")
    , BOX_SAMPLES_NORMAL1("box/samples/normal/1/")
    , BOX_SAMPLES_NORMAL2("box/samples/normal/2/")
    , BOX_SAMPLES_NORMAL3("box/samples/normal/3/")
    , BOX_SAMPLES_NORMAL_NEGATIVE("box/samples/normal/negative/")
    , BOX_SAMPLES_LOW0("box/samples/low/0/")

    // BRACKETS ---------------------------------------------------------------

    , BRACKETS_TEST("brackets/test/")
    , BRACKETS_SAMPLES_NORMAL0("brackets/samples/normal/0/")

    // CIRCLE -----------------------------------------------------------------

    , CIRCLE_TEST0("circle/test/0/")
    , CIRCLE_TEST1("circle/test/1/")
    , CIRCLE_FEATURES("circle/features/")
    , CIRCLE_SAMPLES_NORMAL0("circle/samples/normal/0/")
    , CIRCLE_SAMPLES_LOW0("circle/samples/low/0/")

    //
    ;

    private final String resourceDirectory;

    private ImageCollectionCatalog(final String path) {
        this.resourceDirectory = "de/dbo/samples/image/houghtransform/data/" + path;
    }

    private ImageCollectionCatalog() {
        resourceDirectory = null;
    }

    public String path() {
        return resourceDirectory;
    }
}
