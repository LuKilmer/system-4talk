package test;

import java.util.ArrayList;

import model.*;

public class Teste2 {

	public static void main(String[] args) {

		try {
			Individual p1 = new Individual("Maria", "hipopotámo", false);
			Individual p2 = new Individual("João", "12345", false);
			Mensagem msg1 = new Mensagem(41, p1, p2, "OIeee!");
			Mensagem msg6 = new Mensagem(81, p2, p1, "Oi?");
			Mensagem msg2 = new Mensagem(91, p1, p2, "Sou a Maria...");
			Mensagem msg7 = new Mensagem(111, p2, p1, "ata, o que vc quer?");
			Mensagem msg3 = new Mensagem(421, p1, p2, "só queria dar um oi...");
			Mensagem msg8 = new Mensagem(451, p2, p1, "já deu?");
			Mensagem msg4 = new Mensagem(471, p1, p2, "sim... ;-;");
			Mensagem msg5 = new Mensagem(491, p1, p2, "desculpa atrapalhar");
			ArrayList<Mensagem> msgList = new ArrayList<>();
			msgList.add(msg1);
			msgList.add(msg2);
			msgList.add(msg3);
			msgList.add(msg4);
			msgList.add(msg5);
			msgList.add(msg6);
			msgList.add(msg7);
			msgList.add(msg8);
			for (Mensagem ok : msgList) {
				System.out.println(ok.getTexto());
			}
			System.out.println();

			ArrayList<Mensagem> msgListorganized = new ArrayList<>();
			int sizeList = msgList.size();
			for (int i = 0; i < sizeList; i++) {
				Mensagem minMsg = msgList.get(0);
				for (Mensagem msg : msgList) {
					if (msg.getId() < minMsg.getId()) {
						minMsg = msg;
					}
				}
				msgList.remove(minMsg);
				msgListorganized.add(minMsg);
			}
			for (Mensagem ok : msgListorganized) {
				System.out.println(ok.getTexto());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
