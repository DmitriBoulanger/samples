package de.dbo.samples.image.houghtransform;

/**
 * Available image collections for development and JUnit-tests.
 * Images are allocated as test-resources (src/test/resources/data)
 *
 * @author D.Boulanger ITyX GmbH
 *
 */
public enum ImageCollections {

    TEST

    //
    // SIGNATUR ----------------------------------------------------------------
    //

    , SIGNATURE_TEST0
    , SIGNATURE_TEST1
    , SIGNATURE_TEST2

    , SIGNATURE_PROBLEM
    , SIGNATURE_PERFORMANCE_CHECKED
    , SIGNATURE_PERFORMANCE_UNCHECKED
    , SIGNATURE_PERFORMANCE_ERROR

    //
    // BOX --------------------------------------------------------------------
    //

    , BOX_TEST0
    , BOX_TEST1
    , BOX_FEATURES

    , BOX_SAMPLES_NORMAL0
    , BOX_SAMPLES_NORMAL1
    , BOX_SAMPLES_NORMAL2
    , BOX_SAMPLES_NORMAL3
    , BOX_SAMPLES_NORMAL_NEGATIVE

    , BOX_SAMPLES_LOW0

    //
    // BRACKETS ---------------------------------------------------------------
    //

    , BRACKETS_TEST
    , BRACKETS_SAMPLES_NORMAL0

    //
    // CIRCLE -----------------------------------------------------------------
    //

    , CIRCLE_TEST0
    , CIRCLE_TEST1
    , CIRCLE_FEATURES
    , CIRCLE_SAMPLES_NORMAL0
    , CIRCLE_SAMPLES_LOW0


}
