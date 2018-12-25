package ui;
import javax.swing.*;

import console.*;
import exception.ATMException;
import exception.BalanceNotEnoughException;
import exception.LoanException;
import ui.Dialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BussinessPanel extends JFrame{
	JLabel id=new JLabel();
	JLabel name=new JLabel();
	JLabel email=new JLabel();
	JLabel personId=new JLabel();
	JLabel kind=new JLabel();
	JLabel balance=new JLabel();
	JLabel credit=new JLabel();
	JLabel loan=new JLabel();
	JButton button=new JButton("���");
	JButton button2=new JButton("ȡ��");
	JButton button3=new JButton("�޸ĸ�����Ϣ");
	JButton button4=new JButton("�˳���¼");
	JButton button5=new JButton("����");
	JButton button6=new JButton("����");
	JButton button7=new JButton("ת��");
	Bank bank=Bank.getBank();
	JFrame frame=new JFrame("������");
	JLabel label_f=new JLabel("��������:		");
	JTextField field_f=new JTextField();
	JButton button_f=new JButton("ȷ��");
	JButton button_f_cannel=new JButton("ȡ��");
	JFrame frame2=new JFrame("ת��");
	JLabel label_tr_obj=new JLabel("������Ŀ���˺ţ�		");
	JLabel label_tr_money=new JLabel("�������		");
	JTextField field_tr_obj=new JTextField();
	JTextField field_tr_money=new JTextField();
	JButton button_tr=new JButton("��ʼת��");
	JButton button_tr_cancel=new JButton("ȡ��");
	String state="";
	long tempId;
	public BussinessPanel(Account account) {
		id.setText(""+account.getId());
		id.setFont(Constant.DIALOG1);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBorder(BorderFactory.createTitledBorder("�����˺�"));
		name.setText(account.getName());
		name.setFont(Constant.DIALOG1);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBorder(BorderFactory.createTitledBorder("����"));
		personId.setText(""+account.getPersonId());
		personId.setFont(Constant.DIALOG1);
		personId.setHorizontalAlignment(SwingConstants.CENTER);
		personId.setBorder(BorderFactory.createTitledBorder("���֤��"));
		email.setText(account.getEmail());
		email.setFont(Constant.DIALOG1);
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setBorder(BorderFactory.createTitledBorder("����"));
		balance.setText(""+account.getBalance());
		balance.setFont(Constant.DIALOG1);
		balance.setForeground(Color.RED);
		balance.setHorizontalAlignment(SwingConstants.CENTER);
		balance.setBorder(BorderFactory.createTitledBorder("���"));
		credit.setFont(Constant.DIALOG1);
		credit.setHorizontalAlignment(SwingConstants.CENTER);
		credit.setBorder(BorderFactory.createTitledBorder("���ö��"));
		loan.setFont(Constant.DIALOG1);
		loan.setHorizontalAlignment(SwingConstants.CENTER);
		loan.setBorder(BorderFactory.createTitledBorder("�����"));
		kind.setFont(Constant.DIALOG1);
		kind.setHorizontalAlignment(SwingConstants.CENTER);
		kind.setBorder(BorderFactory.createTitledBorder("�˻�����"));
		if(account instanceof SavingAccount) {
			if(account instanceof LoanSavingAccount) {
				loan.setText(""+((LoanSavingAccount) account).getLoan());
				kind.setText(Constant.LOAN_SAVING_ACCOUNT);
			}else {
				loan.setText("��������");
				kind.setText(Constant.SAVING_ACCOUNT);
				button5.setEnabled(false);
				button6.setEnabled(false);
			}
			credit.setText("���ö�ȣ���������");
		}else {
			if(account instanceof LoanCreditAccount) {
				loan.setText(""+((LoanCreditAccount) account).getLoan());
				kind.setText(Constant.lOAN_CREDIT_ACCOUNT);
			}else {
				loan.setText("��������");
				kind.setText(Constant.CREDIT_ACCOUNT);
				button5.setEnabled(false);
				button6.setEnabled(false);
			}
			credit.setText(""+((CreditAccount)account).getCeiling());
		}
		button.setFont(Constant.BUTTON2);
		button.setBackground(Color.GRAY);
		button.setForeground(Color.WHITE);
		button.setBorder(BorderFactory.createEtchedBorder());
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				state=Constant.DEPOIST;
				frame.setVisible(true);
			}
		});
		button2.setFont(Constant.BUTTON2);
		button2.setBackground(Color.GRAY);
		button2.setForeground(Color.WHITE);
		button2.setBorder(BorderFactory.createEtchedBorder());
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state=Constant.WITHDRAW;
				frame.setVisible(true);
			}
		});
		button3.setFont(Constant.BUTTON2);
		button3.setForeground(Color.WHITE);
		button3.setHorizontalAlignment(SwingConstants.CENTER);
		button3.setBackground(Color.GRAY);
		button3.setBorder(BorderFactory.createEtchedBorder());
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RegisterPanel(account.getId());
				dispose();
			}
		});
		button4.setFont(Constant.BUTTON2);
		button4.setBackground(Color.GRAY);
		button4.setForeground(Color.WHITE);
		button4.setBorder(BorderFactory.createEtchedBorder());
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MainPanel();
				dispose();
			}
		});
		button5.setFont(Constant.BUTTON2);
		button5.setBackground(Color.GRAY);
		button5.setForeground(Color.WHITE);
		button5.setBorder(BorderFactory.createEtchedBorder());
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state=Constant.LOAN;
				frame.setVisible(true);
			}
		});
		button6.setFont(Constant.BUTTON2);
		button6.setBackground(Color.GRAY);
		button6.setForeground(Color.WHITE);
		button6.setBorder(BorderFactory.createEtchedBorder());
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state=Constant.PAY_LOAN;
				frame.setVisible(true);
			}
		});
		button7.setFont(Constant.BUTTON2);
		button7.setBackground(Color.GRAY);
		button7.setForeground(Color.WHITE);
		button7.setBorder(BorderFactory.createEtchedBorder());
		button7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				state=Constant.TRANSFER;
				frame2.setVisible(true);
			}
		});
		JPanel panel=new JPanel(new GridLayout(8,1));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBackground(Color.WHITE);
		JPanel panel2=new JPanel(new GridLayout(7, 1,0,15));
		panel2.setBackground(Color.DARK_GRAY);
		panel.add(id);
		panel.add(kind);
		panel.add(name);
		panel.add(personId);
		panel.add(email);
		panel.add(balance);
		panel.add(credit);
		panel.add(loan);
		panel2.add(button);
		panel2.add(button2);
		panel2.add(button5);
		panel2.add(button6);
		panel2.add(button7);
		panel2.add(button3);
		panel2.add(button4);
		this.setLayout(new BorderLayout());
		this.add(panel,BorderLayout.CENTER);
		this.add(panel2, BorderLayout.WEST);
		this.setTitle("��ӭ��"+account.getName()+"("+kind.getText()+")");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		label_f.setFont(Constant.DIALOG1);
		button_f.setFont(Constant.BUTTON2);
		button_f_cannel.setFont(Constant.BUTTON2);
		button_f.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					switch (state) {
					case Constant.DEPOIST:
						bank.deposit(account, Double.valueOf(field_f.getText()));
						break;
					case Constant.WITHDRAW:
						bank.withdraw(account, Double.valueOf(field_f.getText()));
						break;
					case Constant.LOAN:
						bank.loan(account, Double.valueOf(field_f.getText()));
						break;
					case Constant.PAY_LOAN:
						bank.payLoan(account, Double.valueOf(field_f.getText()));
						break;
					}
					new BussinessPanel(account);
					frame.dispose();
					dispose();
				} catch (NumberFormatException e) {
					new Dialog("��������");
					frame.dispose();
				} catch (BalanceNotEnoughException e) {
					new Dialog("�������ö�Ȳ��㣡");
					frame.dispose();
				} catch (LoanException e) {
					new Dialog("�����쳣");
					frame.dispose();
				}
			}
		});
		button_f_cannel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		label_tr_obj.setFont(Constant.DIALOG2);
		label_tr_money.setFont(Constant.DIALOG2);
		button_tr.setFont(Constant.BUTTON1);
		button_tr_cancel.setFont(Constant.BUTTON1);
		
		JPanel panel_1=new JPanel(new GridLayout(1,2,5,5));
		JPanel panel_2=new JPanel();
		panel_1.add(label_f);
		panel_1.add(field_f);
		panel_2.add(button_f);
		panel_2.add(button_f_cannel);
		frame.setTitle("������");
		frame.setLayout(new BorderLayout());
		frame.add(panel_1,BorderLayout.CENTER);
		frame.add(panel_2,BorderLayout.SOUTH);
		frame.setSize(400, 120);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(false);
		
		JPanel panel_3=new JPanel(new GridLayout(2,2,5,5));
		JPanel panel_4=new JPanel();
		panel_3.add(label_tr_obj);panel_3.add(field_tr_obj);
		panel_3.add(label_tr_money);panel_3.add(field_tr_money);
		panel_4.add(button_tr);panel_4.add(button_tr_cancel);
		button_tr.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					bank.transfer(account,Long.valueOf(field_tr_obj.getText()), Double.valueOf(field_tr_money.getText()));
					frame2.dispose();
					new BussinessPanel(account);
					dispose();
					new Dialog("ת�˳ɹ���");
				} catch (NumberFormatException e) {
					new Dialog("��������");
				} catch (BalanceNotEnoughException e) {
					new Dialog("����");
				} catch (ATMException e) {
					new Dialog("�˻������ڣ�ת��ʧ��");
				}
			}
		});
		button_tr_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
			}
		});
		frame2.setLayout(new BorderLayout());
		frame2.add(panel_3,BorderLayout.CENTER);
		frame2.add(panel_4, BorderLayout.SOUTH);
		frame2.setSize(400, 170);
		frame2.setLocationRelativeTo(null);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setVisible(false);
	}
}
