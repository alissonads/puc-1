#pragma once
#include "CardInstant.h"

class Hero;

class CardInstantAttack : public CardInstant
{
public:
	CardInstantAttack(string, InstantType, int);
	virtual void print();
	virtual void runAction(Hero *);
	~CardInstantAttack();

private:
	int attack;

};
