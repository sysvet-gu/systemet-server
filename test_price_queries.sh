#!/bin/bash

BASE_URL='http://localhost:8080/search/products/all?'
ALL_PRICES='min_price=0&max_price=999999'

num_prods=$(GET "$BASE_URL$ALL_PRICES" | grep '"name"' | wc -l)

expected=20

if [ "$num_prods" != "$expected" ]
then
    echo "Get products with price >=0 and price <=999999 failed"
    echo "Wrong number of products: got $num_prods expectec $expected"
    exit 1
else
    echo "Get products with price >=0 and price <=999999 passed"
fi

PRICES_BETWEEN_100_AND_200='min_price=100&max_price=200'
num_prods=$(GET "$BASE_URL$PRICES_BETWEEN_100_AND_200" | grep '"name"' | wc -l)

expected=7

if [ "$num_prods" != "$expected" ]
then
    echo "Get products with price >=100 and price <=200 failed"
    echo "Wrong number of products: got $num_prods expectected $expected"
    exit 1
else
    echo "Get products with price >=100 and price <=200 passed"
fi

MIN_ALCOHOL_40_AND_MAX_PRICE_900='min_alcohol=40&max_price=900'
num_prods=$(GET "$BASE_URL$MIN_ALCOHOL_40_AND_MAX_PRICE_900" | grep '"name"' | wc -l)

expected=1
if [ "$num_prods" != "$expected" ]
then
    echo "Get products with alcohol >=40 and price <=900 failed"
    echo "Wrong number of products: got $num_prods expectec $expected"
    exit 1
else
    echo "Get products with alcohol >=40 and price <=900 passed"
fi

MIN_ALCOHOL_10_MAX_PRICE_200='min_alcohol=10&max_price=200'
expected=7

num_prods=$(GET "$BASE_URL$MIN_ALCOHOL_10_MAX_PRICE_200" | grep '"name"' | wc -l)
if [ "$num_prods" != "$expected" ]
then
    echo "Get products with alcohol >=10 and price <=200 failed"
    echo "Wrong number of products: got $num_prods expectec $expected"
    exit 1
else
    echo "Get products with alcohol >=10 and price <=200 passed"
fi
