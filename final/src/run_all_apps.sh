#!bin/bash

gnome-terminal --window --title="drive_thru" -- bash -c 'classpath=$(find /usr/lib/kafka/libs/ -name "*.jar" -printf "%p:") && java -cp "$classpath." drive_thru.DriveThruMain' &
gnome-terminal --window --title="kitchen" -- bash -c 'classpath=$(find /usr/lib/kafka/libs/ -name "*.jar" -printf "%p:") && java -cp "$classpath." kitchen.KitchenMain' &
gnome-terminal --window --title="cafe" -- bash -c 'classpath=$(find /usr/lib/kafka/libs/ -name "*.jar" -printf "%p:") && java -cp "$classpath." cafe.CafeMain' &
gnome-terminal --window --title="cashier" -- bash -c 'classpath=$(find /usr/lib/kafka/libs/ -name "*.jar" -printf "%p:") && java -cp "$classpath." cashier.CashierMain' &
