import java.util.LinkedList;
/**
 * holds the A6 Grammar rules provided in the previous project
 * @author Minhkhoa Vu	minhkhoavu954@gmail.com
 * @author Anthony Myers
 * @author Juan Espinoza
 * @author Miguel Castorena
 */
public class A6_GrammarRules {
	private LinkedList<Node[]> rules = new LinkedList<Node[]>();
	private AllNodes nodes = new AllNodes();
	
	/**
	 * default constructor that instantiates all A6 Grammar rules
	 * and adds each rule to a linked list
	 */
	public A6_GrammarRules(){
		Node[] rule1 = new Node[4];
		rule1[0] = nodes.kwdprog;
		rule1[1] = nodes.Vargroup;
		rule1[2] = nodes.Fcndefs;
		rule1[3] = nodes.Main;
		
		Node[] rule2 = new Node[2];
		rule2[0] = nodes.kwdmain;
		rule2[1] = nodes.BBlock;
		
		Node[] rule3 = new Node[4];
		rule3[0] = nodes.brace1;
		rule3[1] = nodes.Vargroup;
		rule3[2] = nodes.Stmts;
		rule3[3] = nodes.brace2;
		
		Node[] rule4 = new Node[2];
		rule4[0] = nodes.kwdvars;
		rule4[1] = nodes.PPvarlist;
		
		Node[] rule5 = new Node[1];
		rule5[0] = null;
		
		Node[] rule6 = new Node[3];
		rule6[0] = nodes.parens1;
		rule6[1] = nodes.Varlist;
		rule6[2] = nodes.parens2;
		
		Node[] rule7 = new Node[3];
		rule7[0] = nodes.VarlItem;
		rule7[1] = nodes.semi;
		rule7[2] = nodes.Varlist;
		
		Node[] rule8 = new Node[1];
		rule8[0] = null;
		
		Node[] rule9 = new Node[2];
		rule9[0] = nodes.VarlDecl;
		rule9[1] = nodes.A;
		
		//rule10 crossed out on our rule/grammar list
		Node[] rule10 = new Node[0];
		
		Node[] rule11 = new Node[2];
		rule11[0] = nodes.kwdclass;
		rule11[1] = nodes.VarlItemTail;
		
		//rule12 crossed out on our rule/grammar list
		Node[] rule12 = new Node[0];
		
		Node[] rule13 = new Node[2];
		rule13[0] = nodes.Simplekind;
		rule13[1] = nodes.VarSpec;
		
		Node[] rule14 = new Node[1];
		rule14[0] = nodes.Basekind;
		
		Node[] rule15 = new Node[1];
		rule15[0] = nodes.Classid;
		
		Node[] rule16 = new Node[1];
		rule16[0] = nodes.Int;
		
		Node[] rule17 = new Node[1];
		rule17[0] = nodes.Float;
				
		Node[] rule18 = new Node[1];
		rule18[0] = nodes.string;
		
		Node[] rule19 = new Node[1];
		rule19[0] = nodes.id;
		
		Node[] rule20 = new Node[2];
		rule20[0] = nodes.id;
		rule20[1] = nodes.VarSpecTail;
		
		//rule21 crossed out on our rule/grammar list
		Node[] rule21 = new Node[0];
		
		Node[] rule22 = new Node[1];
		rule22[0] = nodes.Deref_id;
		
		Node[] rule23 = new Node[1];
		rule23[0] = nodes.id;
		
		Node[] rule24 = new Node[2];
		rule24[0] = nodes.Varid;
		rule24[1] = nodes.KKint;
		
		Node[] rule25 = new Node[3];
		rule25[0] = nodes.bracket1;
		rule25[1] = nodes.Int;
		rule25[2] = nodes.bracket2;
		
		Node[] rule26 = new Node[2];
		rule26[0] = nodes.Deref;
		rule26[1] = nodes.id;
		
		Node[] rule27 = new Node[1];
		rule27[0] = nodes.aster;
		
		Node[] rule28 = new Node[1];
		rule28[0] = nodes.Expr;
		
		Node[] rule29 = new Node[1];
		rule29[0] = nodes.BBexprs;
		
		Node[] rule30 = new Node[2];
		rule30[0] = nodes.brace1;
		rule30[1] = nodes.BBexprsTail;
		
		//rule31 removed
		Node[] rule31 = new Node[0];
		
		Node[] rule32 = new Node[2];
		rule32[0] = nodes.Expr;
		rule32[1] = nodes.Moreexprs;
		
		Node[] rule33 = new Node[2];
		rule33[0] = nodes.comma;
		rule33[1] = nodes.Exprlist;
		
		Node[] rule34 = new Node[1];
		rule34[0] = null;
		
		Node[] rule35 = new Node[2];
		rule35[0] = nodes.kwdclass;
		rule35[1] = nodes.Classid;
		
		Node[] rule36 = new Node[2];
		rule36[0] = nodes.Classheader;
		rule36[1] = nodes.BBClassitems;
		
		Node[] rule37 = new Node[3];
		rule37[0] = nodes.brace1;
		rule37[1] = nodes.Classitems;
		rule37[2] = nodes.brace2;
		
		Node[] rule38 = new Node[3];
		rule38[0] = nodes.Classdecl;
		rule38[1] = nodes.Classmom;
		rule38[2] = nodes.Interfaces;
		
		Node[] rule39 = new Node[2];
		rule39[0] = nodes.colon;
		rule39[1] = nodes.Classid;
		
		Node[] rule40 = new Node[1];
		rule40[0] = null;
		
		Node[] rule41 = new Node[1];
		rule41[0] = nodes.ClassitemsTail;
		
		//rule42 removed
		Node[] rule42 = new Node[0];
		
		Node[] rule43 = new Node[1];
		rule43[0] = nodes.Class_ctrl;
		
		Node[] rule44 = new Node[1];
		rule44[0] = nodes.Varlist;
		
		Node[] rule45 = new Node[1];
		rule45[0] = nodes.Mddecls;
		
		Node[] rule46 = new Node[2];
		rule46[0] = nodes.colon;
		rule46[1] = nodes.id;
		
		Node[] rule47 = new Node[3];
		rule47[0] = nodes.colon;
		rule47[1] = nodes.Classid;
		rule47[2] = nodes.Interfaces;
		
		Node[] rule48 = new Node[1];
		rule48[0] = null;
		
		Node[] rule49 = new Node[2];
		rule49[0] = nodes.Mdheader;
		rule49[1] = nodes.Mddecls;
		
		Node[] rule50 = new Node[1];
		rule50[0] = null;
		
		Node[] rule51 = new Node[4];
		rule51[0] = nodes.kwdfcn;
		rule51[1] = nodes.Md_id;
		rule51[2] = nodes.PParmlist;
		rule51[3] = nodes.Retkind;
		
		Node[] rule52 = new Node[3];
		rule52[0] = nodes.Classid;
		rule52[1] = nodes.colon;
		rule52[2] = nodes.Fcnid;
		
		Node[] rule53 = new Node[2];
		rule53[0] = nodes.Fcndef;
		rule53[1] = nodes.Fcndefs;
		
		Node[] rule54 = new Node[1];
		rule54[0] = null;
		
		Node[] rule55 = new Node[2];
		rule55[0] = nodes.Fcnheader;
		rule55[1] = nodes.BBlock;
		
		Node[] rule56 = new Node[4];
		rule56[0] = nodes.kwdfcn;
		rule56[1] = nodes.Fcnid;
		rule56[2] = nodes.PParmlist;
		rule56[3] = nodes.Retkind;
		
		Node[] rule57 = new Node[1];
		rule57[0] = nodes.id;
		
		Node[] rule58 = new Node[1];
		rule58[0] = nodes.Simplekind;
		
		Node[] rule59 = new Node[2];
		rule59[0] = nodes.parens1;
		rule59[1] = nodes.PParmlistTail;
		
		//rule60 crossed out
		Node[] rule60 = new Node[0];
		
		Node[] rule61 = new Node[2];
		rule61[0] = nodes.VarSpec;
		rule61[1] = nodes.More_varspecs;
		
		Node[] rule62 = new Node[2];
		rule62[0] = nodes.comma;
		rule62[1] = nodes.Varspecs;
		
		Node[] rule63 = new Node[1];
		rule63[0] = null;
		
		Node[] rule64 = new Node[2];
		rule64[0] = nodes.parens1;
		rule64[1] = nodes.parens2;
		
		Node[] rule65 = new Node[3];
		rule65[0] = nodes.Stmt;
		rule65[1] = nodes.semi;
		rule65[2] = nodes.Stmts;
		
		Node[] rule66 = new Node[1];
		rule66[0] = null;
		
		Node[] rule67 = new Node[2];
		rule67[0] = nodes.id;
		rule67[1] = nodes.StmtTail;
		
		//rule68 crossed out
		Node[] rule68 = new Node[0];
		
		Node[] rule69 = new Node[1];
		rule69[0] = nodes.Stif;
		
		Node[] rule70 = new Node[1];
		rule70[0] = nodes.Stwhile;
		
		Node[] rule71 = new Node[1];
		rule71[0] = nodes.Stprint;
		
		Node[] rule72 = new Node[1];
		rule72[0] = nodes.Strtn;
		
		Node[] rule73 = new Node[3];
		rule73[0] = nodes.Lval;
		rule73[1] = nodes.equal;
		rule73[2] = nodes.Expr;
		
		Node[] rule74 = new Node[1];
		rule74[0] = nodes.Varid;
		
		Node[] rule75 = new Node[1];
		rule75[0] = nodes.Aref;
		
		Node[] rule76 = new Node[1];
		rule76[0] = nodes.Deref_id;
		
		Node[] rule77 = new Node[2];
		rule77[0] = nodes.Varid;
		rule77[1] = nodes.KKexpr;
		
		Node[] rule78 = new Node[3];
		rule78[0] = nodes.bracket1;
		rule78[1] = nodes.Expr;
		rule78[2] = nodes.bracket2;
		
		Node[] rule79 = new Node[2];
		rule79[0] = nodes.Fcnid;
		rule79[1] = nodes.PPexprs;
		
		Node[] rule80 = new Node[2];
		rule80[0] = nodes.parens1;
		rule80[1] = nodes.PPexprsTail;
		
		Node[] rule81 = new Node[1];
		rule81[0] = nodes.PPonly;
		
		Node[] rule82 = new Node[4];
		rule82[0] = nodes.kwdif;
		rule82[1] = nodes.PPexpr;
		rule82[2] = nodes.BBlock;
		rule82[3] = nodes.Elsepart;
		
		Node[] rule83 = new Node[4];
		rule83[0] = nodes.kwdelseif;
		rule83[1] = nodes.PPexpr;
		rule83[2] = nodes.BBlock;
		rule83[3] = nodes.Elsepart;
		
		Node[] rule84 = new Node[2];
		rule84[0] = nodes.kwdelse;
		rule84[1] = nodes.BBlock;
		
		Node[] rule85 = new Node[1];
		rule85[0] = null;
		
		Node[] rule86 = new Node[3];
		rule86[0] = nodes.kwdwhile;
		rule86[1] = nodes.PPexpr;
		rule86[2] = nodes.BBlock;
		
		Node[] rule87 = new Node[2];
		rule87[0] = nodes.kprint;
		rule87[1] = nodes.PPexprs;
		
		Node[] rule88 = new Node[2];
		rule88[0] = nodes.kwdreturn;
		rule88[1] = nodes.Expr;
		
		Node[] rule89 = new Node[1];
		rule89[0] = nodes.kwdreturn;
		
		Node[] rule90 = new Node[3];
		rule90[0] = nodes.parens1;
		rule90[1] = nodes.Expr;
		rule90[2] = nodes.parens2;
		
		Node[] rule91 = new Node[1];
		rule91[0] = nodes.Rterm;
		
		Node[] rule92 = new Node[1];
		rule92[0] = nodes.Term;
		
		Node[] rule93 = new Node[1];
		rule93[0] = nodes.Fact;
		
		Node[] rule94 = new Node[1];
		rule94[0] = nodes.Basekind;
		
		Node[] rule95 = new Node[2];
		rule95[0] = nodes.id;
		rule95[1] = nodes.FactTail;

		Node[] rule96 = new Node[1];
		rule96[0] = nodes.Addrof_id;

		Node[] rule97 = new Node[1];
		rule97[0] = nodes.Fcall;

		Node[] rule98 = new Node[1];
		rule98[0] = nodes.PPexpr;
		
		Node[] rule99 = new Node[2];
		rule99[0] = nodes.ampersand;
		rule99[1] = nodes.id;
		
		Node[] rule100 = new Node[1];
		rule100[0] = nodes.opeq;
		
		Node[] rule101 = new Node[1];
		rule101[0] = nodes.opne;
		
		Node[] rule102 = new Node[1];
		rule102[0] = nodes.Lthan;
		
		Node[] rule103 = new Node[1];
		rule103[0] = nodes.ople;
		
		Node[] rule104 = new Node[1];
		rule104[0] = nodes.opge;
		
		Node[] rule105 = new Node[1];
		rule105[0] = nodes.Gthan;

		Node[] rule106 = new Node[1];
		rule106[0] = nodes.angle1;
		
		Node[] rule107 = new Node[1];
		rule107[0] = nodes.angle2;
		
		Node[] rule108 = new Node[1];
		rule108[0] = nodes.plus;
		
		Node[] rule109 = new Node[1];
		rule109[0] = nodes.minus;
		
		Node[] rule110 = new Node[1];
		rule110[0] = nodes.aster;
		
		Node[] rule111 = new Node[1];
		rule111[0] = nodes.slash;
		
		Node[] rule112 = new Node[1];
		rule112[0] = nodes.caret;
		
		Node[] rule113 = new Node[1];
		rule113[0] = null;
		
		Node[] rule114 = new Node[3];
		rule114[0] = nodes.Oprel;
		rule114[1] = nodes.Rterm;
		rule114[2] = nodes.Q;

		Node[] rule115 = new Node[3];
		rule115[0] = nodes.Opadd;
		rule115[1] = nodes.Term;
		rule115[2] = nodes.Q;

		Node[] rule116 = new Node[3];
		rule116[0] = nodes.Opmul;
		rule116[1] = nodes.Fact;
		rule116[2] = nodes.Q;
		
		Node[] rule117 = new Node[1];
		rule117[0] = null;
		
		Node[] rule118 = new Node[2];
		rule118[0] = nodes.equal;
		rule118[1] = nodes.Varinit;
		
		Node[] rule119 = new Node[1];
		rule119[0] = nodes.ClassDefTail;
		
		Node[] rule120 = new Node[1];
		rule120[0] = null;
		
		Node[] rule121 = new Node[3];
		rule121[0] = nodes.Classmom;
		rule121[1] = nodes.Interfaces;
		rule121[2] = nodes.BBClassitems;
		
		Node[] rule122 = new Node[1];
		rule122[0] = null;
		
		Node[] rule123 = new Node[1];
		rule123[0] = nodes.KKint;
		
		Node[] rule124 = new Node[2];
		rule124[0] = nodes.Exprlist;
		rule124[1] = nodes.brace2;
		
		Node[] rule125 = new Node[1];
		rule125[0] = nodes.brace2;
		
		Node[] rule126 = new Node[1];
		rule126[0] = null;
		
		Node[] rule127 = new Node[2];
		rule127[0] = nodes.ClassgroupTail;
		rule127[1] = nodes.Classitems;
		
		Node[] rule128 = new Node[1];
		rule128[0] = nodes.Class_ctrl;
		
		Node[] rule129 = new Node[1];
		rule129[0] = nodes.VarlistTail;
		
		Node[] rule130 = new Node[2];
		rule130[0] = nodes.Mdheader;
		rule130[1] = nodes.Mddecls;
		
		Node[] rule131 = new Node[3];
		rule131[0] = nodes.VarlItem;
		rule131[1] = nodes.semi;
		rule131[2] = nodes.Varlist;
		
		Node[] rule132 = new Node[2];
		rule132[0] = nodes.Varspecs;
		rule132[1] = nodes.parens2;
		
		Node[] rule133 = new Node[1];
		rule133[0] = nodes.parens2;
		
		Node[] rule134 = new Node[1];
		rule134[0] = nodes.PPexprs;
		
		Node[] rule135 = new Node[2];
		rule135[0] = nodes.equal;
		rule135[1] = nodes.Expr;
		
		Node[] rule136 = new Node[1];
		rule136[0] = null;
		
		Node[] rule137 = new Node[1];
		rule137[0] = nodes.KKexpr;
		
		Node[] rule138 = new Node[2];
		rule138[0] = nodes.Exprlist;
		rule138[1] = nodes.parens2;
		
		Node[] rule139 = new Node[1];
		rule139[0] = nodes.parens2;
		
		Node[] rule140 = new Node[1];
		rule140[0] = nodes.Expr;
		
		Node[] rule141 = new Node[1];
		rule141[0] = null;
		
		Node[] rule142 = new Node[1];
		rule142[0] = null;
		
		Node[] rule143 = new Node[1];
		rule143[0] = nodes.KKexpr;
		
		Node[] rule144 = new Node[1];
		rule144[0] = nodes.PPexpr;
		
		
		rules.add(rule1);
		rules.add(rule2);
		rules.add(rule3);
		rules.add(rule4);
		rules.add(rule5);
		rules.add(rule6);
		rules.add(rule7);
		rules.add(rule8);
		rules.add(rule9);
		rules.add(rule10);
		rules.add(rule11);
		rules.add(rule12);
		rules.add(rule13);
		rules.add(rule14);
		rules.add(rule15);
		rules.add(rule16);
		rules.add(rule17);
		rules.add(rule18);
		rules.add(rule19);
		rules.add(rule20);
		rules.add(rule21);
		rules.add(rule22);
		rules.add(rule23);
		rules.add(rule24);
		rules.add(rule25);
		rules.add(rule26);
		rules.add(rule27);
		rules.add(rule28);
		rules.add(rule29);
		rules.add(rule30);
		rules.add(rule31);
		rules.add(rule32);
		rules.add(rule33);
		rules.add(rule34);
		rules.add(rule35);
		rules.add(rule36);
		rules.add(rule37);
		rules.add(rule38);
		rules.add(rule39);
		rules.add(rule40);
		rules.add(rule41);
		rules.add(rule42);
		rules.add(rule43);
		rules.add(rule44);
		rules.add(rule45);
		rules.add(rule46);
		rules.add(rule47);
		rules.add(rule48);
		rules.add(rule49);
		rules.add(rule50);
		rules.add(rule51);
		rules.add(rule52);
		rules.add(rule53);
		rules.add(rule54);
		rules.add(rule55);
		rules.add(rule56);
		rules.add(rule57);
		rules.add(rule58);
		rules.add(rule59);
		rules.add(rule60);
		rules.add(rule61);
		rules.add(rule62);
		rules.add(rule63);
		rules.add(rule64);
		rules.add(rule65);
		rules.add(rule66);
		rules.add(rule67);
		rules.add(rule68);
		rules.add(rule69);
		rules.add(rule70);
		rules.add(rule71);
		rules.add(rule72);
		rules.add(rule73);
		rules.add(rule74);
		rules.add(rule75);
		rules.add(rule76);
		rules.add(rule77);
		rules.add(rule78);
		rules.add(rule79);
		rules.add(rule80);
		rules.add(rule81);
		rules.add(rule82);
		rules.add(rule83);
		rules.add(rule84);
		rules.add(rule85);
		rules.add(rule86);
		rules.add(rule87);
		rules.add(rule88);
		rules.add(rule89);
		rules.add(rule90);
		rules.add(rule91);
		rules.add(rule92);
		rules.add(rule93);
		rules.add(rule94);
		rules.add(rule95);
		rules.add(rule96);
		rules.add(rule97);
		rules.add(rule98);
		rules.add(rule99);
		rules.add(rule100);
		rules.add(rule101);
		rules.add(rule102);
		rules.add(rule103);
		rules.add(rule104);
		rules.add(rule105);
		rules.add(rule106);
		rules.add(rule107);
		rules.add(rule108);
		rules.add(rule109);
		rules.add(rule110);
		rules.add(rule111);
		rules.add(rule112);
		rules.add(rule113);
		rules.add(rule114);
		rules.add(rule115);
		rules.add(rule116);
		rules.add(rule117);
		rules.add(rule118);
		rules.add(rule119);
		rules.add(rule120);
		rules.add(rule121);
		rules.add(rule122);
		rules.add(rule123);
		rules.add(rule124);
		rules.add(rule125);
		rules.add(rule126);
		rules.add(rule127);
		rules.add(rule128);
		rules.add(rule129);
		rules.add(rule130);
		rules.add(rule131);
		rules.add(rule132);
		rules.add(rule133);
		rules.add(rule134);
		rules.add(rule135);
		rules.add(rule136);
		rules.add(rule137);
		rules.add(rule138);
		rules.add(rule139);
		rules.add(rule140);		
		rules.add(rule141);
		rules.add(rule142);
		rules.add(rule143);
		rules.add(rule144);
	}
	
	public LinkedList<Node[]> getRules(){
		return rules;
	}
}
