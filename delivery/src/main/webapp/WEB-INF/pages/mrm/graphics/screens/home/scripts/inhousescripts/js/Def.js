TreeGridLoaded( // JSONP header, to be possible to load from xxx_Jsonp data source


    {
        Cfg: {
            MainCol: "name",
            ShowDeleted: "0",
            Style: "ExtJS",
            MinRowHeight: "25",
            ConstHeight:"1",
            /*ConstHeight:"1"   ,
             Hover:"2"     ,*/
          /*  Filtering: "1",
            StandardFilter: "3",*/
/*
            SearchExpression:"5",
*/
            SearchMethod:"1",
            SearchAction:"Mark",
            Expanded: false

        },

        Solid: {


                Id:"123"   ,
                Space:"5" ,
                Kind:"Toolbar",
                Cells:"Print, Expand, ZoomIn, ZoomOut"


            /*Tabber:{
                Cells:"SHOW,CHILD,HIDE,LEAF,EMPTY,NODE",
                SHOWButtonText:"Show all found",
                SHOWOnCheck:"",
                SHOW:"1",
                SHOWTip:"Shows all rows"
            } ,


            Search: {
                Cells: "Expression,Search,Actions,Filter,Select,Mark,Find,Clear,Help",
                ActionsRange:"1",
                Actions:"Filter;Mark;Find",
                Space: "1",

                ExpressionAction: "Last"
*/
            }



       ,

        LeftCols: [
            { Name: "Panel", Type: "Panel", WidthPad: "17", Buttons: "Select,Delete,Move,Copy"}     ,
            { Name: "name", Type: "Text", Width: "500", WidthPad: "17"}

        ],

      /*  Head: [
            {
                Filter: {
                    *//*nameCanEdit:1,
                     id:"Filter1",
                     Height:"22",
                     Range:1,
                     Filter:1,*//*

                    nameRange: "1",
                    nameButton: "Defaults",
                    nameDefaults: "|*FilterOff|*RowsAll",
                    name: "test",
                    nameFilter: "1",
                    nameCanEdit: "1",

                    managerRange: "1",
                    managerButton: "Defaults",
                    managerDefaults: "|*FilterOff|*RowsAll",
                    manager: "test",
                    managerFilter: "1",
                    managerCanEdit: "1"
                }
            }
        ],*/


        Cols: [

            { Name: "currency", Type: "Text", Visible: 0, CanFilter: 1}   ,
            { Name: "manager", Visible: 0, Width: "75", Type: "Text", WidthPad: "17", CanFilter: 1},
            { Name: "budgetOwner", Visible: 0, Width: "75", Type: "Text", WidthPad: "17", MinWidth: 105, CanFilter: 1},
            { Name: "budget", Visible: 0, Width: "75", Type: "Text", WidthPad: "17", CanFilter: 1},
            { Name: "startDate", Visible: 0, Width: "75", Type: "Date", Format: "dd/MM/yyyy", WidthPad: "17", MinWidth: 105, CanFilter: 1},
            { Name: "endDate", Visible: 0, Width: "75", Type: "Date", Format: "dd/MM/yyyy", WidthPad: "17", MinWidth: 105, CanFilter: 1},
            { Name: "ganttChart", Visible: 0, Type: "Gantt", GanttStart: "startDate", GanttEnd: "endDate", CanFilter: 1,
                GanttSlack: "Slack",
                GanttFormatObject: "MainPrev",
                GanttFormat: "*name*",
                GanttMainTip: "<div style='padding-bottom:5px;font-weight:bold'>*name*</div>" +
                    "<div style='padding-bottom:5px;'><span  style='text-align: left;float: left;font-size: 11px'>Start:</span><span style='text-align: right;margin-left:65px;float: right;font-size: 11px'>*startDate*</span></div>" +
                    "<div style='padding-bottom:5px;'><span  style='text-align: left;float: left;font-size: 11px'>End:</span><span style='text-align: right;margin-left:65px;float: right;font-size: 11px'>*endDate*</span></div>" +
                    "<div style='padding-bottom:5px;'><span  style='text-align: left;float: left;font-size: 11px'>Manager:</span><span style='text-align: right;margin-left:65px;float: right;font-size: 11px'>*manager*</span></div>" +
                    "<div><span  style='text-align: left;float: left;font-size: 11px'>Budget:</span><span style='text-align: right;float: right;margin-left:65px;font-size: 11px'>*budget**currency*</span></div>",
                GanttZoom: "months",
                GanttClass: "GanttOrange",
                GanttZoomList: [


                    { Name: 'years', GanttUnits: 'M', GanttChartRound: 'y', GanttWidth: 65,
                        GanttHeader1: 'y#yyyy', GanttHeader2: 'M#MMM',
                        GanttBackground: 'M#1/1/2008#3', GanttRight: "0", GanttLeft: "0"
                    },

                    { Name: 'months', GanttUnits: 'w', GanttChartRound: 'M', GanttWidth: 170,
                        GanttHeader1: 'M#MMMM yyyy', GanttHeader2: 'w#Week ddddddd', GanttHeader3: 'd#dd',
                        GanttBackground: 'w#1/6/2008#3', GanttRight: "0", GanttLeft: "0"
                    },

                    { Name: 'weeks', GanttUnits: 'd', GanttChartRound: 'w', GanttWidth: 100,
                        GanttHeader1: 'w#MMMM yyyy Week ddddddd', GanttHeader2: 'd#dddd',
                        GanttBackground: 'd#1/1/2008#3'
                    },

                    { Name: 'days', GanttUnits: 'h', GanttChartRound: 'd', GanttWidth: 30,
                        GanttBackground: 'h#01#3',
                        GanttHeader1: 'd#dddd d MMMM yyyy', GanttHeader2: 'h#HH'
                    }

                ]

            }
        ],

        RightCols: [


        ],

        Header: {name: "Dimensions", startDate: "StartDate", endDate: "EndDate", manager: "Manager", budgetOwner: "Budget Owner",
            budget: "Budget"},

        /* Solid:
         {
         Search :{
         Cells:"Expression,Search,Clear",
         ExpressionAction:"Filter,Mark,Find",
         Actions:"Filter,Mark,Find",
         ExpressionTip:"You can search"

         }

         *//* Tabber:{
     Cells:"SHOW",
     SHOWButtonText:"Show all found",
     SHOWOnCheck:"Grid.StandardFilter = 2; Grid.Def.R.CanFilter = 1; Grid.Def.Node.CanFilter = 1; Grid.DoFilter();",
     SHOW:"1",
     SHOWTip:"tip"
     }*//*
     *//* Group:[
     {
     id:"Group", Space:"0", Panel:"0", Cells:"Zoom", CanPrint:"5", ListLeft:"2",
     ZoomType:"SelectGanttZoom", ZoomLeft:'2', ZoomWidth:'170', ZoomPrintHPage:'1'
     }

     ]*//*
     },
     */





        Toolbar: {
            Space: '0',
            Cells: 'Custom1,Filter, Mark,Base,Finish,Crit,Dep,Resources,Use,Avail,Free,Err,Empty',


            BaseType: 'Date', BaseCanEdit: '1', BaseWidth: '75', BaseUndo: '1', BaseLeft: '5',
            BaseLabel: 'Baseline',
            BaseFormat: "ddd M/d/yyyy, <span style='color:blue'>HH</span>",
            BaseEditFormat: 'M/d/yyyy HH',
            BaseFormula: 'Grid.GetGanttBase()',
            BaseOnChange: 'Grid.SetGanttBase(Value,2);',
            BaseHtmlPrefixFormula: 'Grid.Cols.ganttChart.GanttBase===""?"<span style=color:gray>":"<span>"',
            BaseHtmlPostfix: '</span>',
            BaseTip: 'Starting date of the whole project.<br>No task should start before it.<br>It is also used when correcting dependencies.',

            FinishType: 'Date', FinishCanEdit: '1', FinishWidth: '75', FinishUndo: '1', FinishLeft: '5',
            FinishLabel: 'Finish date',
            FinishFormat: "ddd M/d/yyyy, '<span style='color:blue'>'HH'</span>&amp;nbsp;'",
            FinishEditFormat: 'M/d/yyyy HH',
            FinishFormula: 'Grid.GetGanttFinish()',
            FinishOnChange: 'Grid.SetGanttFinish(Value);',
            FinishHtmlPrefixFormula: 'Grid.Cols.ganttChart.GanttFinish===""?"<span style=color:gray>":"<span>"',
            FinishHtmlPostfix: '</span>',
            FinishTip: 'Finish date of the whole project.<br>If grayed, it is calculated from the last task.<br>It is used to calculate critical path.',

            CritType: 'Bool', CritNoColor: '1', CritCanEdit: '1', CritLeft: '5', CritCanFocus: '0',
            CritLabelRight: '',

            DepType: 'Select',
            DepDefaults: '|Ignore problems|Auto correct problems|Alert and confirm problems',
            DepFormula: 'Grid.GetDefaultsValue(Row,Col,Grid.Cols.ganttChart.GanttCorrectDependencies)',

/*
             DepOnChange: 'var v=Grid.GetDefaultsIndex(Row,Col,Value);Grid.Cols.G.GanttCorrectDependencies=v;Grid.Cols.G.GanttCheckExclude=v;Grid.SaveCfg();',
*/

            DepTip: 'If the dependent tasks will be updated after some task move or resize',
            DepWidth: '75',
            DepLeft: '5',


            ResourcesType: 'SelectGanttResources',

            ResourcesLeft: '5',
            ResourcesWidth: '75',

            UseType: 'Bool',
            UseNoColor: '1',
            UseCanEdit: '1',
            UseLeft: '5',
            UseCanFocus: '0',

            UseLabelRight: 'Used resources',
            Use: '1',


/*
             UseOnChange: 'Grid.Def.Resource.GGanttAvailability : (Row.Avail?",N2#3":"") + (Row.Use?",N2#1":"") + (Row.Use&amp;&amp;Row.Avail?",N2#8":"") + (Row.Free?",N2#5":"") + (Row.Err?",N2#6":""); if(!Row.Avail&amp;&amp;!Row.Free&amp;&amp;!Row.Err) {   var F = Grid.GetRows(Grid.Foot); Grid.StartUpdate();   for(var i:0;i&lt;F.length;i++) if(Value) Grid.ShowRow(F[i]); else Grid.HideRow(F[i]);  Grid.EndUpdate(); } else Grid.RefreshGantt();',
*/



            AvailType: 'Bool',
            AvailNoColor: '1',
            AvailCanEdit: '1',
            AvailLeft: '5',
            AvailCanFocus: '0',

            AvailLabelRight: 'Available resources',
            Avail: '1',

/*
             AvailOnChange: ' Grid.Def.Resource.GGanttAvailability : (Row.Avail?",N2#3":"") + (Row.Use?",N2#1":"") + (Row.Use&amp;&amp;Row.Avail?",N2#8":"") + (Row.Free?",N2#5":"") + (Row.Err?",N2#6":"");  if(!Row.Use&amp;&amp;!Row.Free&amp;&amp;!Row.Err) {      var F : Grid.GetRows(Grid.Foot); Grid.StartUpdate();      for(var i=0;i&lt;F.length;i++) if(Value) Grid.ShowRow(F[i]); else Grid.HideRow(F[i]);      Grid.EndUpdate();  } else Grid.RefreshGantt();',
*/

            FreeType: 'Bool',
            FreeNoColor: '1',
            FreeCanEdit: '1',
            FreeLeft: '5',
            FreeCanFocus: '0',
            FreeLabelRight: 'Free resources',
            Free: '0',


/*
             FreeOnChange: 'Grid.Def.Resource.GGanttAvailability = (Row.Avail?",N2#3":"") + (Row.Use?",N2#1":"") + (Row.Use&amp;&amp;Row.Avail?",N2#8":"") + (Row.Free?",N2#5":"") + (Row.Err?",N2#6":""); if(!Row.Use&amp;&amp;!Row.Avail&amp;&amp;!Row.Err) {     var F = Grid.GetRows(Grid.Foot); Grid.StartUpdate();      for(var i=0;i&lt;F.length;i++) if(Value) Grid.ShowRow(F[i]); else Grid.HideRow(F[i]);     Grid.EndUpdate();      }  else Grid.RefreshGantt(); ',
*/


            ErrType: 'Bool',
            ErrNoColor: '1',
            ErrCanEdit: '1',
            ErrCanFocus: '0',
            ErrLeft: '5',
            ErrLabelRight: 'Resources errors',
            Err: '0',

/*
             ErrOnChange: 'Grid.Def.Resource.GGanttAvailability = (Row.Avail?",N2#3":"") + (Row.Use?",N2#1":"") + (Row.Use&amp;&amp;Row.Avail?",N2#8":"") + (Row.Free?",N2#5":"") + (Row.Err?",N2#6":"");   if(!Row.Use&amp;&amp;!Row.Avail&amp;&amp;!Row.Free) {      var F = Grid.GetRows(Grid.Foot); Grid.StartUpdate();       for(var i=0;i&lt;F.length;i++) if(Value) Grid.ShowRow(F[i]); else Grid.HideRow(F[i]);     Grid.EndUpdate(); }  else Grid.RefreshGantt(); ',
*/


            EmptyRelWidth: "1",
            EmptyType: "Html",

            SearchLabel:"Search"  ,
            SearchCanEdit: '1',
            SearchWidth: '75',
            SearchActionsRange:"1",
            SearchActions:"Filter;Mark;Find",
            SearchSpace: "5",

            SearchCells: "Expression,Search",

            SearchExpressionAction: "Filter"


        }

       /* Group:{
            Search: {
                ActionsRange:"1",
                Actions:"Filter;Mark;Find",
                Space: "5",
                Cells: "Expression,Search",
                ExpressionAction: "Filter"
            }
        }   ,
*/



        /* Solid :{

         Group:{
         id:"Group",
         Space:0,
         Panel:0,
         Cells:"Zoom",


         ZoomType:'SelectGanttZoom',
         ZoomHtmlPrefix:'<b>Zoom to <span style="color:blue;">',
         ZoomHtmlPostfix:'</span></b>',
         ZoomLeft:'5',
         ZoomWidth:'170',

         ExLabel:"<b style='color:blue;'>Holidays</b>",
         ExWidth:"50",
         ExLeft:"5" ,
         ExType:"Select",
         ExOnClickSideDefaults : "Grid.ShowGanttCalendars(Row,Col)",
         ExFormula:"Grid.Cols.G.GanttExclude" ,
         ExOnChange:"Grid.ChangeExclude(Value);",
         ExUndo:"1"     ,
         ExTip:"Global calendar for the whole project",

         HidExType:'Bool',
         HidExNoColor:'1',
         HidExLeft:'5',
         HidExCanFocus:'0',
         HidExLabelRight:'Hide holidays'   ,
         HidExFormula:'Grid.Cols.G.GanttHideExclude'  ,
         HidExOnChange:'Grid.SetHideExclude(Value)' ,
         HidExCanEditFormula:'gantthasexclude()!=0' ,
         HidTip:'Hides dates excluded by the global calendar' ,

         CalType:'Bool',
         CalNoColor:'1',
         CalLeft:'5',
         CalCanFocus:'0' ,
         CalCanEdit:'1',

         CalLabelRight:'Custom calendars' ,

         CalFormula:'Grid.Cols.G.GanttCalendar',
         CalOnChange:'Grid.ChangeExclude(Value?"T":"","GanttCalendar"); if(Value) Grid.ShowCol("T"); else Grid.HideCol("T");',
         CalTip:'Permits custom calendars for individual tasks'
         },

         Topbar2 :{
         id:'Project',
         Space:'0',
         Panel:'0',
         Cells:'Base,Finish,Crit,Dep',
         Recalc:'-1',

         BaseType:'Date', BaseCanEdit:'1', BaseWidth:'100', BaseUndo:'1', BaseLeft:'5' ,
         BaseLabel:'Baseline'       ,
         BaseFormat:"ddd M/d/yyyy, '<span style='color:blue'>'HH'</span>'" ,
         BaseEditFormat:'M/d/yyyy HH'  ,
         BaseFormula:'Grid.GetGanttBase()'  ,
         BaseOnChange:'Grid.SetGanttBase(Value,2);' ,
         BaseHtmlPrefixFormula:'Grid.Cols.G.GanttBase===""?"<span style=&apos;color:gray;&apos;>":"<span>"'  ,
         BaseHtmlPostfix:'</span>'      ,
         BaseTip:'Starting date of the whole project.<br>No task should start before it.<br>It is also used when correcting dependencies.',

         FinishType:'Date' ,FinishCanEdit:'1', FinishWidth:'100', FinishUndo:'1', FinishLeft:'5'  ,
         FinishLabel:'Finish date'  ,
         FinishFormat:"ddd M/d/yyyy, '<span style='color:blue'>'HH'</span>&amp;nbsp;'" ,
         FinishEditFormat:'M/d/yyyy HH'  ,
         FinishFormula:'Grid.GetGanttFinish()'  ,
         FinishOnChange:'Grid.SetGanttFinish(Value);' ,
         FinishHtmlPrefixFormula:'Grid.Cols.G.GanttFinish===""?"<span style=&apos;color:gray;&apos;>":"<span>"' ,
         FinishHtmlPostfix:'</span>'  ,
         FinishTip:'Finish date of the whole project.<br>If grayed, it is calculated from the last task.<br>It is used to calculate critical path.'   ,

         CritType:'Bool', CritNoColor:'1', CritCanEdit:'1', CritLeft:'5', CritCanFocus:'0',
         CritLabelRight:'Show <b style="color:blue;">critical path</b> only' ,
         CritOnChange:'Grid.SetFilter("Crit",Value?"L!==&apos;&apos; &amp;&amp; L<=Grid.Cols.G.GanttMinSlack &amp;&amp; C!=100":"")' ,

         DepType:'Select' ,
         DepDefaults:'|Ignore problems|Auto correct problems|Alert and confirm problems' ,
         DepFormula:'Grid.GetDefaultsValue(Row,Col,Grid.Cols.G.GanttCorrectDependencies)' ,
         DepOnChange:'var v=Grid.GetDefaultsIndex(Row,Col,Value);Grid.Cols.G.GanttCorrectDependencies=v;Grid.Cols.G.GanttCheckExclude=v;Grid.SaveCfg();',
         DepTip:'If the dependent tasks will be updated after some task move or resize' ,
         DepWidth:'130' ,
         DepLeft:'5'


         }
         }*/
    }

)

