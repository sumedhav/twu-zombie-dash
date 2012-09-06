insert into zombie_task values ('charles_task', cast('2b4e62ac-4a90-4c71-9486-e67bded6248e' AS uuid));
insert into zombie_question values (cast('372b7e07-4cbd-47e3-90cd-7f166c2c25df' AS uuid), 'Where is Red Fort', cast('2b4e62ac-4a90-4c71-9486-e67bded6248e' AS uuid));
insert into zombie_question values (cast('1bf3b297-2d15-4228-8830-1ebe15e35100' AS uuid), 'Is it lunch time?', cast('2b4e62ac-4a90-4c71-9486-e67bded6248e' AS uuid));
insert into zombie_option values (1, cast('372b7e07-4cbd-47e3-90cd-7f166c2c25df' AS uuid),  'Delhi', true);
insert into zombie_option values (2, cast('372b7e07-4cbd-47e3-90cd-7f166c2c25df' AS uuid),  'Paris', false);
insert into zombie_option values (3, cast('372b7e07-4cbd-47e3-90cd-7f166c2c25df' AS uuid),  'New York', false);
insert into zombie_option values (4, cast('1bf3b297-2d15-4228-8830-1ebe15e35100' AS uuid), 'I bet it is', true);
insert into zombie_option values (5, cast('1bf3b297-2d15-4228-8830-1ebe15e35100' AS uuid), 'No thanks, fasting at the moment', false);