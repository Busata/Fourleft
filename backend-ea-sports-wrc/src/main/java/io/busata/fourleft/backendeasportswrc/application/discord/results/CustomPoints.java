package io.busata.fourleft.backendeasportswrc.application.discord.results;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomPoints {
   // Define a static map to hold the mappings
    private static final Map<Integer, Integer> valueMap;

    // Initialize the map in a static block
    static {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,2500);
        map.put(2,2200);
        map.put(3,2100);
        map.put(4,2025);
        map.put(5,2000);
        map.put(6,1975);
        map.put(7,1960);
        map.put(8,1945);
        map.put(9,1930);
        map.put(10,1925);
        map.put(11,1920);
        map.put(12,1915);
        map.put(13,1910);
        map.put(14,1905);
        map.put(15,1900);
        map.put(16,1895);
        map.put(17,1890);
        map.put(18,1885);
        map.put(19,1880);
        map.put(20,1875);
        map.put(21,1870);
        map.put(22,1865);
        map.put(23,1860);
        map.put(24,1855);
        map.put(25,1850);
        map.put(26,1848);
        map.put(27,1846);
        map.put(28,1844);
        map.put(29,1842);
        map.put(30,1840);
        map.put(31,1838);
        map.put(32,1836);
        map.put(33,1834);
        map.put(34,1832);
        map.put(35,1830);
        map.put(36,1828);
        map.put(37,1826);
        map.put(38,1824);
        map.put(39,1822);
        map.put(40,1820);
        map.put(41,1818);
        map.put(42,1816);
        map.put(43,1814);
        map.put(44,1812);
        map.put(45,1810);
        map.put(46,1808);
        map.put(47,1806);
        map.put(48,1804);
        map.put(49,1802);
        map.put(50,1800);
        map.put(51,1798);
        map.put(52,1796);
        map.put(53,1794);
        map.put(54,1792);
        map.put(55,1790);
        map.put(56,1788);
        map.put(57,1786);
        map.put(58,1784);
        map.put(59,1782);
        map.put(60,1780);
        map.put(61,1778);
        map.put(62,1776);
        map.put(63,1774);
        map.put(64,1772);
        map.put(65,1770);
        map.put(66,1768);
        map.put(67,1766);
        map.put(68,1764);
        map.put(69,1762);
        map.put(70,1760);
        map.put(71,1758);
        map.put(72,1756);
        map.put(73,1754);
        map.put(74,1752);
        map.put(75,1750);
        map.put(76,1748);
        map.put(77,1746);
        map.put(78,1744);
        map.put(79,1742);
        map.put(80,1740);
        map.put(81,1738);
        map.put(82,1736);
        map.put(83,1734);
        map.put(84,1732);
        map.put(85,1730);
        map.put(86,1728);
        map.put(87,1726);
        map.put(88,1724);
        map.put(89,1722);
        map.put(90,1720);
        map.put(91,1718);
        map.put(92,1716);
        map.put(93,1714);
        map.put(94,1712);
        map.put(95,1710);
        map.put(96,1708);
        map.put(97,1706);
        map.put(98,1704);
        map.put(99,1702);
        map.put(100,1700);
        map.put(101,1698);
        map.put(102,1696);
        map.put(103,1694);
        map.put(104,1692);
        map.put(105,1690);
        map.put(106,1688);
        map.put(107,1686);
        map.put(108,1684);
        map.put(109,1682);
        map.put(110,1680);
        map.put(111,1678);
        map.put(112,1676);
        map.put(113,1674);
        map.put(114,1672);
        map.put(115,1670);
        map.put(116,1668);
        map.put(117,1666);
        map.put(118,1664);
        map.put(119,1662);
        map.put(120,1660);
        map.put(121,1658);
        map.put(122,1656);
        map.put(123,1654);
        map.put(124,1652);
        map.put(125,1650);
        map.put(126,1648);
        map.put(127,1646);
        map.put(128,1644);
        map.put(129,1642);
        map.put(130,1640);
        map.put(131,1638);
        map.put(132,1636);
        map.put(133,1634);
        map.put(134,1632);
        map.put(135,1630);
        map.put(136,1628);
        map.put(137,1626);
        map.put(138,1624);
        map.put(139,1622);
        map.put(140,1620);
        map.put(141,1618);
        map.put(142,1616);
        map.put(143,1614);
        map.put(144,1612);
        map.put(145,1610);
        map.put(146,1608);
        map.put(147,1606);
        map.put(148,1604);
        map.put(149,1602);
        map.put(150,1600);
        map.put(151,1598);
        map.put(152,1596);
        map.put(153,1594);
        map.put(154,1592);
        map.put(155,1590);
        map.put(156,1588);
        map.put(157,1586);
        map.put(158,1584);
        map.put(159,1582);
        map.put(160,1580);
        map.put(161,1578);
        map.put(162,1576);
        map.put(163,1574);
        map.put(164,1572);
        map.put(165,1570);
        map.put(166,1568);
        map.put(167,1566);
        map.put(168,1564);
        map.put(169,1562);
        map.put(170,1560);
        map.put(171,1558);
        map.put(172,1556);
        map.put(173,1554);
        map.put(174,1552);
        map.put(175,1550);
        map.put(176,1548);
        map.put(177,1546);
        map.put(178,1544);
        map.put(179,1542);
        map.put(180,1540);
        map.put(181,1538);
        map.put(182,1536);
        map.put(183,1534);
        map.put(184,1532);
        map.put(185,1530);
        map.put(186,1528);
        map.put(187,1526);
        map.put(188,1524);
        map.put(189,1522);
        map.put(190,1520);
        map.put(191,1518);
        map.put(192,1516);
        map.put(193,1514);
        map.put(194,1512);
        map.put(195,1510);
        map.put(196,1508);
        map.put(197,1506);
        map.put(198,1504);
        map.put(199,1502);
        map.put(200,1500);
        map.put(201,1498);
        map.put(202,1496);
        map.put(203,1494);
        map.put(204,1493);
        map.put(205,1491);
        map.put(206,1489);
        map.put(207,1487);
        map.put(208,1486);
        map.put(209,1484);
        map.put(210,1482);
        map.put(211,1480);
        map.put(212,1479);
        map.put(213,1477);
        map.put(214,1475);
        map.put(215,1473);
        map.put(216,1472);
        map.put(217,1470);
        map.put(218,1468);
        map.put(219,1466);
        map.put(220,1465);
        map.put(221,1463);
        map.put(222,1461);
        map.put(223,1459);
        map.put(224,1458);
        map.put(225,1456);
        map.put(226,1454);
        map.put(227,1452);
        map.put(228,1451);
        map.put(229,1449);
        map.put(230,1447);
        map.put(231,1445);
        map.put(232,1444);
        map.put(233,1442);
        map.put(234,1440);
        map.put(235,1438);
        map.put(236,1437);
        map.put(237,1435);
        map.put(238,1433);
        map.put(239,1431);
        map.put(240,1430);
        map.put(241,1428);
        map.put(242,1426);
        map.put(243,1424);
        map.put(244,1423);
        map.put(245,1421);
        map.put(246,1419);
        map.put(247,1417);
        map.put(248,1416);
        map.put(249,1414);
        map.put(250,1412);
        map.put(251,1410);
        map.put(252,1409);
        map.put(253,1407);
        map.put(254,1405);
        map.put(255,1403);
        map.put(256,1402);
        map.put(257,1400);
        map.put(258,1398);
        map.put(259,1396);
        map.put(260,1395);
        map.put(261,1393);
        map.put(262,1391);
        map.put(263,1389);
        map.put(264,1388);
        map.put(265,1386);
        map.put(266,1384);
        map.put(267,1382);
        map.put(268,1381);
        map.put(269,1379);
        map.put(270,1377);
        map.put(271,1375);
        map.put(272,1374);
        map.put(273,1372);
        map.put(274,1370);
        map.put(275,1368);
        map.put(276,1367);
        map.put(277,1365);
        map.put(278,1363);
        map.put(279,1361);
        map.put(280,1360);
        map.put(281,1358);
        map.put(282,1356);
        map.put(283,1354);
        map.put(284,1353);
        map.put(285,1351);
        map.put(286,1349);
        map.put(287,1347);
        map.put(288,1346);
        map.put(289,1344);
        map.put(290,1342);
        map.put(291,1340);
        map.put(292,1339);
        map.put(293,1337);
        map.put(294,1335);
        map.put(295,1333);
        map.put(296,1332);
        map.put(297,1330);
        map.put(298,1328);
        map.put(299,1326);
        map.put(300,1325);
        map.put(301,1323);
        map.put(302,1321);
        map.put(303,1319);
        map.put(304,1318);
        map.put(305,1316);
        map.put(306,1314);
        map.put(307,1312);
        map.put(308,1311);
        map.put(309,1309);
        map.put(310,1307);
        map.put(311,1305);
        map.put(312,1304);
        map.put(313,1302);
        map.put(314,1300);
        map.put(315,1298);
        map.put(316,1297);
        map.put(317,1295);
        map.put(318,1293);
        map.put(319,1291);
        map.put(320,1290);
        map.put(321,1288);
        map.put(322,1286);
        map.put(323,1284);
        map.put(324,1283);
        map.put(325,1281);
        map.put(326,1279);
        map.put(327,1277);
        map.put(328,1276);
        map.put(329,1274);
        map.put(330,1272);
        map.put(331,1270);
        map.put(332,1269);
        map.put(333,1267);
        map.put(334,1265);
        map.put(335,1263);
        map.put(336,1262);
        map.put(337,1260);
        map.put(338,1258);
        map.put(339,1256);
        map.put(340,1255);
        map.put(341,1253);
        map.put(342,1251);
        map.put(343,1249);
        map.put(344,1248);
        map.put(345,1246);
        map.put(346,1244);
        map.put(347,1242);
        map.put(348,1241);
        map.put(349,1239);
        map.put(350,1237);
        map.put(351,1235);
        map.put(352,1234);
        map.put(353,1232);
        map.put(354,1230);
        map.put(355,1228);
        map.put(356,1227);
        map.put(357,1225);
        map.put(358,1223);
        map.put(359,1221);
        map.put(360,1220);
        map.put(361,1218);
        map.put(362,1216);
        map.put(363,1214);
        map.put(364,1213);
        map.put(365,1211);
        map.put(366,1209);
        map.put(367,1207);
        map.put(368,1206);
        map.put(369,1204);
        map.put(370,1202);
        map.put(371,1200);
        map.put(372,1199);
        map.put(373,1197);
        map.put(374,1195);
        map.put(375,1193);
        map.put(376,1192);
        map.put(377,1190);
        map.put(378,1188);
        map.put(379,1186);
        map.put(380,1185);
        map.put(381,1183);
        map.put(382,1181);
        map.put(383,1179);
        map.put(384,1178);
        map.put(385,1176);
        map.put(386,1174);
        map.put(387,1172);
        map.put(388,1171);
        map.put(389,1169);
        map.put(390,1167);
        map.put(391,1165);
        map.put(392,1164);
        map.put(393,1162);
        map.put(394,1160);
        map.put(395,1158);
        map.put(396,1157);
        map.put(397,1155);
        map.put(398,1153);
        map.put(399,1151);
        map.put(400,1150);
        map.put(401,1148);
        map.put(402,1146);
        map.put(403,1144);
        map.put(404,1142);
        map.put(405,1140);
        map.put(406,1138);
        map.put(407,1136);
        map.put(408,1134);
        map.put(409,1132);
        map.put(410,1130);
        map.put(411,1128);
        map.put(412,1126);
        map.put(413,1124);
        map.put(414,1122);
        map.put(415,1120);
        map.put(416,1118);
        map.put(417,1116);
        map.put(418,1114);
        map.put(419,1112);
        map.put(420,1110);
        map.put(421,1108);
        map.put(422,1106);
        map.put(423,1104);
        map.put(424,1102);
        map.put(425,1100);
        map.put(426,1098);
        map.put(427,1096);
        map.put(428,1094);
        map.put(429,1092);
        map.put(430,1090);
        map.put(431,1088);
        map.put(432,1086);
        map.put(433,1084);
        map.put(434,1082);
        map.put(435,1080);
        map.put(436,1078);
        map.put(437,1076);
        map.put(438,1074);
        map.put(439,1072);
        map.put(440,1070);
        map.put(441,1068);
        map.put(442,1066);
        map.put(443,1064);
        map.put(444,1062);
        map.put(445,1060);
        map.put(446,1058);
        map.put(447,1056);
        map.put(448,1054);
        map.put(449,1052);
        map.put(450,1050);
        map.put(451,1048);
        map.put(452,1046);
        map.put(453,1044);
        map.put(454,1042);
        map.put(455,1040);
        map.put(456,1038);
        map.put(457,1036);
        map.put(458,1034);
        map.put(459,1032);
        map.put(460,1030);
        map.put(461,1028);
        map.put(462,1026);
        map.put(463,1024);
        map.put(464,1022);
        map.put(465,1020);
        map.put(466,1018);
        map.put(467,1016);
        map.put(468,1014);
        map.put(469,1012);
        map.put(470,1010);
        map.put(471,1008);
        map.put(472,1006);
        map.put(473,1004);
        map.put(474,1002);
        map.put(475,1000);
        map.put(476,998);
        map.put(477,996);
        map.put(478,994);
        map.put(479,992);
        map.put(480,990);
        map.put(481,988);
        map.put(482,986);
        map.put(483,984);
        map.put(484,982);
        map.put(485,980);
        map.put(486,978);
        map.put(487,976);
        map.put(488,974);
        map.put(489,972);
        map.put(490,970);
        map.put(491,968);
        map.put(492,966);
        map.put(493,964);
        map.put(494,962);
        map.put(495,960);
        map.put(496,958);
        map.put(497,956);
        map.put(498,954);
        map.put(499,952);
        map.put(500,950);
        map.put(501,948);
        map.put(502,946);
        map.put(503,944);
        map.put(504,942);
        map.put(505,940);
        map.put(506,938);
        map.put(507,936);
        map.put(508,934);
        map.put(509,932);
        map.put(510,930);
        map.put(511,928);
        map.put(512,926);
        map.put(513,924);
        map.put(514,922);
        map.put(515,920);
        map.put(516,918);
        map.put(517,916);
        map.put(518,914);
        map.put(519,912);
        map.put(520,910);
        map.put(521,908);
        map.put(522,906);
        map.put(523,904);
        map.put(524,902);
        map.put(525,900);
        map.put(526,898);
        map.put(527,896);
        map.put(528,894);
        map.put(529,892);
        map.put(530,890);
        map.put(531,888);
        map.put(532,886);
        map.put(533,884);
        map.put(534,882);
        map.put(535,880);
        map.put(536,878);
        map.put(537,876);
        map.put(538,874);
        map.put(539,872);
        map.put(540,870);
        map.put(541,868);
        map.put(542,866);
        map.put(543,864);
        map.put(544,862);
        map.put(545,860);
        map.put(546,858);
        map.put(547,856);
        map.put(548,854);
        map.put(549,852);
        map.put(550,850);
        map.put(551,848);
        map.put(552,846);
        map.put(553,844);
        map.put(554,842);
        map.put(555,840);
        map.put(556,838);
        map.put(557,836);
        map.put(558,834);
        map.put(559,832);
        map.put(560,830);
        map.put(561,828);
        map.put(562,826);
        map.put(563,824);
        map.put(564,822);
        map.put(565,820);
        map.put(566,818);
        map.put(567,816);
        map.put(568,814);
        map.put(569,812);
        map.put(570,810);
        map.put(571,808);
        map.put(572,806);
        map.put(573,804);
        map.put(574,802);
        map.put(575,800);
        map.put(576,798);
        map.put(577,796);
        map.put(578,794);
        map.put(579,792);
        map.put(580,790);
        map.put(581,788);
        map.put(582,786);
        map.put(583,784);
        map.put(584,782);
        map.put(585,780);
        map.put(586,778);
        map.put(587,776);
        map.put(588,774);
        map.put(589,772);
        map.put(590,770);
        map.put(591,768);
        map.put(592,766);
        map.put(593,764);
        map.put(594,762);
        map.put(595,760);
        map.put(596,758);
        map.put(597,756);
        map.put(598,754);
        map.put(599,752);
        map.put(600,750);
        map.put(601,748);
        map.put(602,747);
        map.put(603,745);
        map.put(604,744);
        map.put(605,742);
        map.put(606,741);
        map.put(607,739);
        map.put(608,738);
        map.put(609,736);
        map.put(610,735);
        map.put(611,733);
        map.put(612,732);
        map.put(613,730);
        map.put(614,729);
        map.put(615,727);
        map.put(616,726);
        map.put(617,724);
        map.put(618,723);
        map.put(619,721);
        map.put(620,720);
        map.put(621,718);
        map.put(622,717);
        map.put(623,715);
        map.put(624,714);
        map.put(625,712);
        map.put(626,711);
        map.put(627,709);
        map.put(628,708);
        map.put(629,706);
        map.put(630,705);
        map.put(631,703);
        map.put(632,702);
        map.put(633,700);
        map.put(634,699);
        map.put(635,697);
        map.put(636,696);
        map.put(637,694);
        map.put(638,693);
        map.put(639,691);
        map.put(640,690);
        map.put(641,688);
        map.put(642,687);
        map.put(643,685);
        map.put(644,684);
        map.put(645,682);
        map.put(646,681);
        map.put(647,679);
        map.put(648,678);
        map.put(649,676);
        map.put(650,675);
        map.put(651,673);
        map.put(652,672);
        map.put(653,670);
        map.put(654,669);
        map.put(655,667);
        map.put(656,666);
        map.put(657,664);
        map.put(658,663);
        map.put(659,661);
        map.put(660,660);
        map.put(661,658);
        map.put(662,657);
        map.put(663,655);
        map.put(664,654);
        map.put(665,652);
        map.put(666,651);
        map.put(667,649);
        map.put(668,648);
        map.put(669,646);
        map.put(670,645);
        map.put(671,643);
        map.put(672,642);
        map.put(673,640);
        map.put(674,639);
        map.put(675,637);
        map.put(676,636);
        map.put(677,634);
        map.put(678,633);
        map.put(679,631);
        map.put(680,630);
        map.put(681,628);
        map.put(682,627);
        map.put(683,625);
        map.put(684,624);
        map.put(685,622);
        map.put(686,621);
        map.put(687,619);
        map.put(688,618);
        map.put(689,616);
        map.put(690,615);
        map.put(691,613);
        map.put(692,612);
        map.put(693,610);
        map.put(694,609);
        map.put(695,607);
        map.put(696,606);
        map.put(697,604);
        map.put(698,603);
        map.put(699,601);
        map.put(700,600);
        map.put(701,598);
        map.put(702,596);
        map.put(703,594);
        map.put(704,592);
        map.put(705,590);
        map.put(706,588);
        map.put(707,586);
        map.put(708,584);
        map.put(709,582);
        map.put(710,580);
        map.put(711,578);
        map.put(712,576);
        map.put(713,574);
        map.put(714,572);
        map.put(715,570);
        map.put(716,568);
        map.put(717,566);
        map.put(718,564);
        map.put(719,562);
        map.put(720,560);
        map.put(721,558);
        map.put(722,556);
        map.put(723,554);
        map.put(724,552);
        map.put(725,550);
        map.put(726,548);
        map.put(727,546);
        map.put(728,544);
        map.put(729,542);
        map.put(730,540);
        map.put(731,538);
        map.put(732,536);
        map.put(733,534);
        map.put(734,532);
        map.put(735,530);
        map.put(736,528);
        map.put(737,526);
        map.put(738,524);
        map.put(739,522);
        map.put(740,520);
        map.put(741,518);
        map.put(742,516);
        map.put(743,514);
        map.put(744,512);
        map.put(745,510);
        map.put(746,508);
        map.put(747,506);
        map.put(748,504);
        map.put(749,502);
        map.put(750,500);
        map.put(751,498);
        map.put(752,496);
        map.put(753,494);
        map.put(754,492);
        map.put(755,490);
        map.put(756,488);
        map.put(757,486);
        map.put(758,484);
        map.put(759,482);
        map.put(760,480);
        map.put(761,478);
        map.put(762,476);
        map.put(763,474);
        map.put(764,472);
        map.put(765,470);
        map.put(766,468);
        map.put(767,466);
        map.put(768,464);
        map.put(769,462);
        map.put(770,460);
        map.put(771,458);
        map.put(772,456);
        map.put(773,454);
        map.put(774,452);
        map.put(775,450);
        map.put(776,448);
        map.put(777,446);
        map.put(778,444);
        map.put(779,442);
        map.put(780,440);
        map.put(781,438);
        map.put(782,436);
        map.put(783,434);
        map.put(784,432);
        map.put(785,430);
        map.put(786,428);
        map.put(787,426);
        map.put(788,424);
        map.put(789,422);
        map.put(790,420);
        map.put(791,418);
        map.put(792,416);
        map.put(793,414);
        map.put(794,412);
        map.put(795,410);
        map.put(796,408);
        map.put(797,406);
        map.put(798,404);
        map.put(799,402);
        map.put(800,400);
        map.put(801,398);
        map.put(802,396);
        map.put(803,394);
        map.put(804,392);
        map.put(805,390);
        map.put(806,388);
        map.put(807,386);
        map.put(808,384);
        map.put(809,382);
        map.put(810,380);
        map.put(811,378);
        map.put(812,376);
        map.put(813,374);
        map.put(814,372);
        map.put(815,370);
        map.put(816,368);
        map.put(817,366);
        map.put(818,364);
        map.put(819,362);
        map.put(820,360);
        map.put(821,358);
        map.put(822,356);
        map.put(823,354);
        map.put(824,352);
        map.put(825,350);
        map.put(826,348);
        map.put(827,346);
        map.put(828,344);
        map.put(829,342);
        map.put(830,340);
        map.put(831,338);
        map.put(832,336);
        map.put(833,334);
        map.put(834,332);
        map.put(835,330);
        map.put(836,328);
        map.put(837,326);
        map.put(838,324);
        map.put(839,322);
        map.put(840,320);
        map.put(841,318);
        map.put(842,316);
        map.put(843,314);
        map.put(844,312);
        map.put(845,310);
        map.put(846,308);
        map.put(847,306);
        map.put(848,304);
        map.put(849,302);
        map.put(850,300);
        map.put(851,298);
        map.put(852,296);
        map.put(853,294);
        map.put(854,292);
        map.put(855,290);
        map.put(856,288);
        map.put(857,286);
        map.put(858,284);
        map.put(859,282);
        map.put(860,280);
        map.put(861,278);
        map.put(862,276);
        map.put(863,274);
        map.put(864,272);
        map.put(865,270);
        map.put(866,268);
        map.put(867,266);
        map.put(868,264);
        map.put(869,262);
        map.put(870,260);
        map.put(871,258);
        map.put(872,256);
        map.put(873,254);
        map.put(874,252);
        map.put(875,250);
        map.put(876,248);
        map.put(877,246);
        map.put(878,244);
        map.put(879,242);
        map.put(880,240);
        map.put(881,238);
        map.put(882,236);
        map.put(883,234);
        map.put(884,232);
        map.put(885,230);
        map.put(886,228);
        map.put(887,226);
        map.put(888,224);
        map.put(889,222);
        map.put(890,220);
        map.put(891,218);
        map.put(892,216);
        map.put(893,214);
        map.put(894,212);
        map.put(895,210);
        map.put(896,208);
        map.put(897,206);
        map.put(898,204);
        map.put(899,202);
        map.put(900,200);
        map.put(901,198);
        map.put(902,196);
        map.put(903,194);
        map.put(904,192);
        map.put(905,190);
        map.put(906,188);
        map.put(907,186);
        map.put(908,184);
        map.put(909,182);
        map.put(910,180);
        map.put(911,178);
        map.put(912,176);
        map.put(913,174);
        map.put(914,172);
        map.put(915,170);
        map.put(916,168);
        map.put(917,166);
        map.put(918,164);
        map.put(919,162);
        map.put(920,160);
        map.put(921,158);
        map.put(922,156);
        map.put(923,154);
        map.put(924,152);
        map.put(925,150);
        map.put(926,148);
        map.put(927,146);
        map.put(928,144);
        map.put(929,142);
        map.put(930,140);
        map.put(931,138);
        map.put(932,136);
        map.put(933,134);
        map.put(934,132);
        map.put(935,130);
        map.put(936,128);
        map.put(937,126);
        map.put(938,124);
        map.put(939,122);
        map.put(940,120);
        map.put(941,118);
        map.put(942,116);
        map.put(943,114);
        map.put(944,112);
        map.put(945,110);
        map.put(946,108);
        map.put(947,106);
        map.put(948,104);
        map.put(949,102);
        map.put(950,100);
        map.put(951,98);
        map.put(952,96);
        map.put(953,94);
        map.put(954,92);
        map.put(955,90);
        map.put(956,88);
        map.put(957,86);
        map.put(958,84);
        map.put(959,82);
        map.put(960,80);
        map.put(961,78);
        map.put(962,76);
        map.put(963,74);
        map.put(964,72);
        map.put(965,70);
        map.put(966,68);
        map.put(967,66);
        map.put(968,64);
        map.put(969,62);
        map.put(970,60);
        map.put(971,58);
        map.put(972,56);
        map.put(973,54);
        map.put(974,52);
        map.put(975,50);
        map.put(976,48);
        map.put(977,46);
        map.put(978,44);
        map.put(979,42);
        map.put(980,40);
        map.put(981,38);
        map.put(982,36);
        map.put(983,34);
        map.put(984,32);
        map.put(985,30);
        map.put(986,28);
        map.put(987,26);
        map.put(988,24);
        map.put(989,22);
        map.put(990,20);
        map.put(991,18);
        map.put(992,16);
        map.put(993,14);
        map.put(994,12);
        map.put(995,10);
        map.put(996,8);
        map.put(997,6);
        map.put(998,4);
        map.put(999,3);
        map.put(1000,2);

        // Make the map unmodifiable
        valueMap = Collections.unmodifiableMap(map);
    }

    // Public method to get the value based on index
    public static Integer getValue(int index) {
        return valueMap.getOrDefault(index, 1);
    }
}
