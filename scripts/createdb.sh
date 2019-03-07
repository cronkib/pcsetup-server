#!/bin/bash
#
# Create database under user pcs
# Create pcsetup schema

export PGPASSWORD=$1

USER=pcs
DATABASE=pcsetup

createdb -O $USER $DATABASE 

psql -U $USER -d $DATABASE -h localhost -a -f createdb.sql
