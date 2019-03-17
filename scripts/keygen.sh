#!/bin/bash
pepper_key="$(openssl rand -rand /dev/urandom 32 -hex)"
pepper_iv="$(openssl rand -rand /dev/urandom 16 -hex)"

jwt_key="$(openssl rand -rand /dev/urandom 32 -hex)"

cd ..

DATA_DIR=$PWD/data
FILENAME=auth.properties

mkdir -p $DATA_DIR
cd $DATA_DIR

if [ -f "$FILENAME" ]; then
	mv $FILENAME $FILENAME.old
fi

echo "pepper.key=$pepper_key" > $FILENAME
echo "pepper.iv=$pepper_iv" >> $FILENAME
echo "jwt.key=$jwt_key" >> $FILENAME
