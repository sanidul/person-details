package com.propay.person.controllers;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativePersonTestSuite extends PersonTestSuite {

    // Execute the same tests but in native mode.
}