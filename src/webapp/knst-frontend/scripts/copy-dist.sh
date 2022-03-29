#!/usr/bin/env bash

cd "${0%/*}" || exit
cd ../public || exit

rm -rf ../../../../build/resources/main/static
mkdir -p ../../../../build/resources/main/static

find . -type d -not -iname '.' -exec cp -r '{}' '../../../../build/resources/main/static/{}' ';'
find . -maxdepth 1 -type f -not -iname 'index.html' -not -iname 'favicon.ico' -not -iname 'app-portal-icon.svg' -exec cp -r '{}' '../../../../build/resources/main/static/{}' ';'

mkdir -p ../../../../build/resources/main/templates

cp index.html ../../../../build/resources/main/templates/index.html
