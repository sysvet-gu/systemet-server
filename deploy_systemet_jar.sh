#!/bin/bash

cd systemet-api/
./build_jar.sh
cd ..
cp systemet-api/systemet.jar webroot/WEB-INF/lib/
