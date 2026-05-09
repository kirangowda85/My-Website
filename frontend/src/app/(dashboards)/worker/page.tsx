import React from 'react';
import Link from 'next/link';

export default function WorkerDashboard() {
  return (
    <div className="py-8">
      <div className="mb-8 flex items-center justify-between">
        <h1 className="text-2xl font-bold text-gray-900">Worker Dashboard</h1>
        <Link href="/jobs" className="rounded-lg bg-blue-600 px-4 py-2 text-sm font-medium text-white hover:bg-blue-700">
          Find New Jobs
        </Link>
      </div>

      <div className="mb-8 grid grid-cols-1 gap-6 md:grid-cols-3">
        <div className="rounded-lg border border-gray-100 bg-white p-6 shadow-sm">
          <h3 className="text-sm font-medium text-gray-500">Active Applications</h3>
          <p className="mt-2 text-3xl font-bold text-gray-900">3</p>
        </div>
        <div className="rounded-lg border border-gray-100 bg-white p-6 shadow-sm">
          <h3 className="text-sm font-medium text-gray-500">Jobs Completed</h3>
          <p className="mt-2 text-3xl font-bold text-gray-900">12</p>
        </div>
        <div className="rounded-lg border border-gray-100 bg-white p-6 shadow-sm">
          <h3 className="text-sm font-medium text-gray-500">Total Earnings</h3>
          <p className="mt-2 text-3xl font-bold text-gray-900">Rs 45,000</p>
        </div>
      </div>

      <div className="overflow-hidden rounded-lg border border-gray-100 bg-white shadow-sm">
        <div className="border-b border-gray-100 px-6 py-4">
          <h2 className="text-lg font-semibold text-gray-900">Upcoming Shifts</h2>
        </div>
        <div className="divide-y divide-gray-100">
          {[
            ['Event Helper - Food Festival', 'Tomorrow, 10:00 AM - 6:00 PM at Downtown Expo Center', 'Accepted', 'Rs 1,200'],
            ['Retail Inventory Assistant', 'Jun 24, 8:00 AM - 2:00 PM at Westside Mall', 'Pending', 'Rs 900'],
          ].map(([title, time, status, pay]) => (
            <div key={title} className="flex items-center justify-between p-6 hover:bg-gray-50">
              <div>
                <h3 className="text-base font-medium text-gray-900">{title}</h3>
                <p className="mt-1 text-sm text-gray-500">{time}</p>
              </div>
              <div className="text-right">
                <span className="inline-flex items-center rounded-full bg-green-100 px-2.5 py-0.5 text-xs font-medium text-green-800">
                  {status}
                </span>
                <p className="mt-1 text-sm font-medium text-gray-900">{pay}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
