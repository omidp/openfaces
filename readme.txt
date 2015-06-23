==============================
 OpenFaces Source Code Package
==============================

Package Contents
-----------------

This package includes:
 * OpenFaces source code -- located in the "source" directory.
 * Libraries required for compiling the OpenFaces source code -- located in the
   "lib" directory.
 * License agreement for OpenFaces -- located in LICENSE file 
 * License agreements for redistributed libraries -- located in
   the "license" directory.
 * Ant build script for compiling the OpenFaces source code into a
   ready-to-deploy jar file -- build.xml file.
 * Other files:
     - ofversion.properties -- Contains OpenFaces version number to be written
       to META-INF/manifest.mf file inside of openfaces.jar.
     - readme.txt -- this file.

Building Source Code
---------------------

Run the "build" target of the Ant build script located in the build.xml file to
compile a ready-to-deploy openfaces.jar package from the sources. Once the build
script execution is complete, the "target" directory will be created in the
root directory of this source code package. This directory will contain the
openfaces.jar file.