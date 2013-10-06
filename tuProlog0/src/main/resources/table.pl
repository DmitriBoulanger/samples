
table_row(Value1,Value2,Value3) :-
   col1(X1,Y1,Width1,Height1,Start1,End1,Value1)
  ,col2(X2,Y2,Width2,Height2,Start2,End2,Value2)
  ,col3(X3,Y3,Width3,Height3,Start3,End3,Value3)
  , (( Y1-Y2 ) * ( Y1-Y2 )) < 10
   , (( Y1-Y3 ) * ( Y1-Y3 )) < 10
   %X2 < X3
  .
  
  table_row(Value1,Value2,'null') :-
   col1(X1,Y1,Width1,Height1,Start1,End1,Value1)
  ,col2(X2,Y2,Width2,Height2,Start2,End2,Value2)
  , (( Y1-Y2 ) * ( Y1-Y2 )) < 10
   %X2 <