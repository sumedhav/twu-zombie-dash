insert into zombie_conference values (cast('372b7e07-4cbd-47e3-90cd-7f166c2c29df' AS uuid), 'Java Conference', 'Java Details', 'Awesome Java Conference For 10 Days', 'Bangalore', '2012-12-12','2012-12-30',10);
insert into zombie_task values ('charles_task', cast('2b4e62ac-4a90-4c71-9486-e67bded6248e' AS uuid),'Charles Some Task' ,cast('372b7e07-4cbd-47e3-90cd-7f166c2c29df' AS uuid));
insert into zombie_question values (cast('372b7e07-4cbd-47e3-90cd-7f166c2c25df' AS uuid), 'Where is Red Fort?', cast('2b4e62ac-4a90-4c71-9486-e67bded6248e' AS uuid));
insert into zombie_question values (cast('1bf3b297-2d15-4228-8830-1ebe15e35100' AS uuid), 'Is it lunch time?', cast('2b4e62ac-4a90-4c71-9486-e67bded6248e' AS uuid));
insert into zombie_option values (cast('8acf8dc8-22a0-4041-bdf4-05c25abe0d64' AS uuid), 'Delhi', true,  cast('372b7e07-4cbd-47e3-90cd-7f166c2c25df' AS uuid));
insert into zombie_option values (cast('86fa57fd-c3bc-40f6-b19d-b0ba3f713243' AS uuid), 'Paris', false, cast('372b7e07-4cbd-47e3-90cd-7f166c2c25df' AS uuid));
insert into zombie_option values (cast('1059e864-af38-4c89-b72e-948bc69ac98b' AS uuid), 'New York', false, cast('372b7e07-4cbd-47e3-90cd-7f166c2c25df' AS uuid));
insert into zombie_option values (cast('84e9bdb8-e508-4cb4-99c6-9de32f93b0c9' AS uuid), 'I bet it is', true,  cast('1bf3b297-2d15-4228-8830-1ebe15e35100' AS uuid));
insert into zombie_option values (cast('c5b8c2c2-d844-44f2-98c6-5b9aa71da2c4' AS uuid), 'No thanks, fasting at the moment', false, cast('1bf3b297-2d15-4228-8830-1ebe15e35100' AS uuid));