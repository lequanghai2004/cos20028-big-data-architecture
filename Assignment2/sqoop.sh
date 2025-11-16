function sqoop_export() {
  local table_name=$1
  local export_dir=$2
  local columns=$3

  echo "Exporting table: $table_name to HDFS directory: $export_dir with columns: $columns"
  
  sqoop export \
    --connect "jdbc:mysql://localhost/indigenous" \
    --username training \
    --password training \
    --table "$table_name" \
    --columns "$columns" \
    --export-dir "/user/training/assignment2/austlang_rdb/$export_dir" \
    --fields-terminated-by $'\t' \
    --m 1 > "log_${table_name}_export.txt" 2>&1

  if [ $? -eq 0 ]; then
    echo "Export of $table_name completed successfully."
  else
    echo "Export of $table_name failed. Check log_${table_name}_export.txt for details."
  fi
}

sqoop_export "lng_id" "lng_id" "lng_id"
sqoop_export "lng_name" "lng_name" "lng_name"
sqoop_export "lng_synonym" "lng_synonym" "lng_synonym"
sqoop_export "lng_thl" "lng_thl" "lng_thl"
sqoop_export "lng_thp" "lng_thp" "lng_thp"
sqoop_export "lng_st" "lng_st" "lng_st"

sqoop_export "rel_code_name" "rel_code_name" "lng_code,lng_name"
sqoop_export "rel_code_synonym" "rel_code_synonym" "lng_code,lng_synonym"
sqoop_export "rel_code_thl" "rel_code_thl" "lng_code,lng_thl"
sqoop_export "rel_code_thp" "rel_code_thp" "lng_code,lng_thp"
sqoop_export "rel_code_st" "rel_code_st" "lng_code,lng_st"
