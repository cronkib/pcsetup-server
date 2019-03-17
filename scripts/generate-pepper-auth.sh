#!/bin/bash
key="$(openssl rand -rand /dev/urandom 32 -hex)"
iv="$(openssl rand -rand /dev/urandom 16 -hex)"

cd ..

DATA_DIR=$PWD/data
FILENAME=auth.properties

mkdir -p $DATA_DIR
cd $DATA_DIR

if [ -f "$FILENAME" ]; then
	mv $FILENAME $FILENAME.old
fi

echo "pepper.key=$key" > $FILENAME
echo "pepper.iv=$iv" >> $FILENAME
